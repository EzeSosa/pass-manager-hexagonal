package com.esosa.pass_manager_hexagonal.infrastructure.security.middleware;

import com.esosa.pass_manager_hexagonal.domain.ports.output.auth.TokenManagementPort;
import com.esosa.pass_manager_hexagonal.infrastructure.security.utils.WhiteListedResources;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenManagementPort tokenManagementPort;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String username;
        final String token = extractTokenFromRequest(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try { username = tokenManagementPort.extractUsernameFromToken(token); }
        catch (ExpiredJwtException e) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT expired");
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (tokenManagementPort.isTokenValid(token, username))
                updateContext(request, userDetails);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return Arrays.stream(WhiteListedResources.WHITE_LISTED_URLS)
                .anyMatch(url -> pathMatcher.match( url, request.getRequestURI() ));
    }

    private void updateContext(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")
                ? authorizationHeader.substring(7)
                : null;
    }

}