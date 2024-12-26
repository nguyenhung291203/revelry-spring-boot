package com.develop.revelryspringboot.constant.error;

import org.springframework.http.HttpStatus;

import com.develop.revelryspringboot.constant.message.ProductErrorMessage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProductErrorCode implements BaseErrorCode {
    PRODUCT_ID_NOT_BLANK(400, ProductErrorMessage.PRODUCT_ID_NOT_BLANK, HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_NOT_BLANK(400, ProductErrorMessage.PRODUCT_NAME_NOT_BLANK, HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_SIZE(400, ProductErrorMessage.PRODUCT_NAME_SIZE, HttpStatus.BAD_REQUEST),
    PRODUCT_DESCRIPTION_SIZE(400, ProductErrorMessage.PRODUCT_DESCRIPTION_SIZE, HttpStatus.BAD_REQUEST),
    PRODUCT_IMAGE_NOT_BLANK(400, ProductErrorMessage.PRODUCT_IMAGE_NOT_BLANK, HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_NOT_NULL(400, ProductErrorMessage.PRODUCT_PRICE_NOT_NULL, HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_INVALID(400, ProductErrorMessage.PRODUCT_PRICE_INVALID, HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_MINIMUM(400, ProductErrorMessage.PRODUCT_PRICE_MINIMUM, HttpStatus.BAD_REQUEST),
    PRODUCT_CATEGORY_NOT_FOUND(404, ProductErrorMessage.PRODUCT_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(404, ProductErrorMessage.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND),
    PRODUCT_ALREADY_EXISTS(409, ProductErrorMessage.PRODUCT_ALREADY_EXISTS, HttpStatus.CONFLICT),
    PRODUCT_IS_ACTIVE_NOT_NULL(400, ProductErrorMessage.PRODUCT_IS_ACTIVE_NOT_NULL, HttpStatus.BAD_REQUEST),
    ;

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
