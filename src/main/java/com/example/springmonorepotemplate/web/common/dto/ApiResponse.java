package com.example.springmonorepotemplate.web.common.dto;

import com.example.springmonorepotemplate.domain.BusinessCode;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private static final ApiResponse<Void> SUCCESS = ApiResponse.from(BusinessCode.OK);
    private static final ApiResponse<Void> FAIL = ApiResponse.from(BusinessCode.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final T data;

    private ApiResponse(BusinessCode responseCode, T data) {
        this(responseCode.getCode(), responseCode.getMessage(), data);
    }

    private ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse<Void> success() {
        return SUCCESS;
    }

    public static ApiResponse<Void> fail() {
        return FAIL;
    }

    public static <T> ApiResponse<T> from(BusinessCode responseCode) {
        return new ApiResponse<>(responseCode, null);
    }

    public static <T> ApiResponse<T> from(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static <T> ApiResponse<T> from(T data) {
        return new ApiResponse<>(BusinessCode.OK, data);
    }
}
