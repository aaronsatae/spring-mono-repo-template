package com.example.springmonorepotemplate.domain;

import lombok.Getter;

@Getter
public enum BusinessCode {
    OK("0000", "success"),
    BAD_REQUEST("0400", "bad request"),
    NOT_FOUND("0404", "not found"),
    INTERNAL_SERVER_ERROR("0500", "internal server error"),
    ;

    private final String code;
    private final String message;

    BusinessCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

