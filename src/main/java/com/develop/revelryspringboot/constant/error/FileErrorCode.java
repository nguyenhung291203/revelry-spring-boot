package com.develop.revelryspringboot.constant.error;

import org.springframework.http.HttpStatus;

import com.develop.revelryspringboot.constant.message.FileErrorMessage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum FileErrorCode implements BaseErrorCode {
    FILE_NOT_FOUND(404, FileErrorMessage.FILE_NOT_FOUND, HttpStatus.NOT_FOUND),
    FILE_UPLOAD_FAILED(500, FileErrorMessage.FILE_UPLOAD_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_TOO_LARGE(413, FileErrorMessage.FILE_TOO_LARGE, HttpStatus.PAYLOAD_TOO_LARGE),
    INVALID_FILE_TYPE(415, FileErrorMessage.INVALID_FILE_TYPE, HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    FILE_DOWNLOAD_FAILED(500, FileErrorMessage.FILE_DOWNLOAD_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

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
