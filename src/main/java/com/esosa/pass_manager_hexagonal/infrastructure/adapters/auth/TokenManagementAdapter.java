package com.esosa.pass_manager_hexagonal.infrastructure.adapters.auth;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.output.auth.TokenManagementPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.function.Function;

public class TokenManagementAdapter implements TokenManagementPort {

    @Value("${jwt.key}")
    private String secretKey;

    @Value("${jwt.access-token-expiration}")
    private Long expirationTime;

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(Collections.emptyMap())
                .setSubject(user.getUsername())
                .setIssuedAt( new Date(System.currentTimeMillis()) )
                .setExpiration( new Date(System.currentTimeMillis() + expirationTime) )
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, String username) {
        String _tokenUsername = extractUsernameFromToken(token);
        return (_tokenUsername.equals(username) && !isTokenExpired(token));
    }

    @Override
    public String extractUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}