package com.msa.middleauth.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Requestuser {
    private String user_id;
    private String user_pwd;
    private String user_name;
    private String user_phone;
}
