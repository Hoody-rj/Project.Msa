package com.msa.middleauth.sign.dto.Response;

public class AuthResponse {
    private String accessToken;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    // getter
    public String getAccessToken() {
        return accessToken;
    }

}
