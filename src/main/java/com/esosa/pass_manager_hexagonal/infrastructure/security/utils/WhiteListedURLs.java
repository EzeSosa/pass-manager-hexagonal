package com.esosa.pass_manager_hexagonal.infrastructure.security.utils;

public class WhiteListedURLs {

    public static String[] WHITE_LISTED_URLS = {
            "/auth/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/"
    };

}