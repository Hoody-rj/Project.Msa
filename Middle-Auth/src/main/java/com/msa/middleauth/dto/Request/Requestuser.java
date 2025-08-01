package com.msa.middleauth.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Requestuser {
    public String user_id;
    public String user_pwd;
    public String user_name;
    public String user_phone;
}
