package com.msa.middleauth.sign.dto.Response;


import lombok.Getter;

@Getter
public class AuthResponse {
    private String accessToken;
    private String resultMsg;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
