package com.geolidth.BackEnd.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtAuthenticationResponse {
    private String token;

    public JwtAuthenticationResponse(@JsonProperty("token") String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}