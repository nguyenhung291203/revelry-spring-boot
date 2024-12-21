package com.develop.revelryspringboot.exception;

import com.develop.revelryspringboot.constant.error.BaseErrorCode;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApiException extends RuntimeException {
    BaseErrorCode errorCode;

    public ApiException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
