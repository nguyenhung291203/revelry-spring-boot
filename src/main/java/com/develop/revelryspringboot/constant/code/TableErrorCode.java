package com.develop.revelryspringboot.constant.error;

import org.springframework.http.HttpStatus;

import com.develop.revelryspringboot.constant.message.TableErrorMessage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TableErrorCode implements BaseErrorCode {
    TABLE_NOT_FOUND(404, TableErrorMessage.TABLE_NOT_FOUND, HttpStatus.NOT_FOUND),
    TABLE_ALREADY_EXISTS(422, TableErrorMessage.TABLE_ALREADY_EXISTS, HttpStatus.CONFLICT),
    NAME_NOT_BLANK(422, TableErrorMessage.TABLE_NAME_NOT_BLANK, HttpStatus.CONFLICT),
    NAME_SIZE(400, TableErrorMessage.TABLE_NAME_SIZE, HttpStatus.BAD_REQUEST),
    CAPACITY_NOT_NULL(400, TableErrorMessage.TABLE_CAPACITY_NOT_NULL, HttpStatus.BAD_REQUEST),
    STATUS_INVALID(400, TableErrorMessage.TABLE_STATUS_INVALID, HttpStatus.BAD_REQUEST),
    TABLE_IS_DELETED(404, TableErrorMessage.TABLE_IS_DELETED, HttpStatus.NOT_FOUND);

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
