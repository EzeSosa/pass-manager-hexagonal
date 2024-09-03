package com.esosa.pass_manager_hexagonal.infrastructure.security.utils;

public class WhiteListedResources {

    public static String[] WHITE_LISTED_URLS = {
            "/auth/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/"
    };

    public static String[] WHITE_LISTED_ROLES = {
            "USER",
            "ADMIN"
    };

}