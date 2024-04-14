package com.geolidth.BackEnd.auth;

import org.springframework.security.core.Authentication;

public class AuthenticationResult {

    private final boolean success;
    private final Authentication authentication;

    public AuthenticationResult(boolean success, Authentication authentication) {
        this.success = success;
        this.authentication = authentication;
    }

    public boolean isSuccess() {
        return success;
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}