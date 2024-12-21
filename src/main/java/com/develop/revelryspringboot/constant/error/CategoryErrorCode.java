package com.develop.revelryspringboot.constant.error;

import org.springframework.http.HttpStatus;

import com.develop.revelryspringboot.constant.message.CategoryErrorMessage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CategoryErrorCode implements BaseErrorCode {
    CATEGORY_NOT_FOUND(404, CategoryErrorMessage.CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND),
    CATEGORY_ALREADY_EXISTS(422, CategoryErrorMessage.CATEGORY_ALREADY_EXISTS, HttpStatus.CONFLICT),
    NAME_NOT_BLANK(422, CategoryErrorMessage.CATEGORY_NAME_NOT_BLANK, HttpStatus.CONFLICT),
    NAME_SIZE(400, CategoryErrorMessage.CATEGORY_NAME_SIZE, HttpStatus.BAD_REQUEST),
    NAME_INVALID(400, CategoryErrorMessage.CATEGORY_NAME_INVALID, HttpStatus.BAD_REQUEST),
    DESCRIPTION_SIZE(400, CategoryErrorMessage.CATEGORY_DESCRIPTION_SIZE, HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
