package com.example.springmonorepotemplate.domain.user;

import com.example.springmonorepotemplate.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(long id) {
        super(String.format("cannot find user(ID: %d).", id));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getErrorCode() {
        return "0404";
    }
}
