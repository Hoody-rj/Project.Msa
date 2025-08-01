package com.msa.middleauth.sign.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Requestuser {
    @NotBlank(message = "User ID는 필수입니다.")
    @NotNull(message = "User ID는 필수입니다.")
    private String user_id;
    @NotBlank(message = "비밀번호는 필수입니다.")
    @NotNull(message = "비밀번호는 필수입니다.")
    private String user_pwd;
    @NotBlank(message = "사용자 이름은 필수입니다.")
    @NotNull(message = "사용자 이름은 필수입니다.")
    private String user_name;
    @NotBlank(message = "전화번호는 필수입니다.")
    @NotNull(message = "전화번호는 필수입니다.")
    private String user_phone;
    // getters and setters
}
