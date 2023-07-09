package com.example.springmonorepotemplate.web.common.handler;

import com.example.springmonorepotemplate.common.exception.BaseException;
import com.example.springmonorepotemplate.domain.BusinessCode;
import com.example.springmonorepotemplate.web.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ApiResponse<Void>> handle(BaseException exception) {
        log.error("Exception {}", exception.getMessage(), exception);

        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(ApiResponse.from(exception.getErrorCode(), exception.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            BindException.class
    })
    protected ApiResponse<Void> handle(Exception exception) {
        log.info("BadRequest {}", exception.getMessage(), exception);

        return ApiResponse.from(BusinessCode.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> unknownExceptionHandler(Exception exception) {
        log.error("InternalServerError {}", exception.getMessage(), exception);

        return ApiResponse.from(BusinessCode.INTERNAL_SERVER_ERROR);
    }
}
