package com.msa.middle_order.cmmn;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AuthList {
    ADMIN("admin"),
    USER("user"),
    MANAGER("manager");

    private final String code;

    AuthList(String code) {
        this.code = code;
    }

    // code 값으로 enum 찾기 메서드
    public static AuthList fromCode(String code) {
        return Arrays.stream(AuthList.values())
                .filter(s -> s.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));
    }
}
