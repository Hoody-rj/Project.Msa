package com.msa.middleauth.sign.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseuserAuth {
    public String user_id;
    public String user_auth;
}
