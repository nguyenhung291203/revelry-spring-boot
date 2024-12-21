package com.develop.revelryspringboot.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.develop.revelryspringboot.constant.error.BaseErrorCode;
import com.develop.revelryspringboot.constant.error.GlobalErrorCode;
import com.develop.revelryspringboot.dto.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ApiResponse<Map<String, String>> generateExceptionResponse(
            BaseErrorCode errorCode, Map<String, String> errors) {
        ApiResponse<Map<String, String>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setResult(errors);
        return apiResponse;
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleApiException(ApiException ex) {
        BaseErrorCode errorCode = ex.getErrorCode();
        ApiResponse<Map<String, String>> apiExceptionResponse = generateExceptionResponse(errorCode, Map.of());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiExceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        BaseErrorCode errorCode = GlobalErrorCode.DATA_VALIDATION_FAILED;

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ApiResponse<Map<String, String>> apiExceptionResponse = generateExceptionResponse(errorCode, Map.of());
        apiExceptionResponse.setResult(errors);

        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiExceptionResponse);
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidate(ValidateException ex) {
        BaseErrorCode errorCode = GlobalErrorCode.DATA_VALIDATION_FAILED;

        ApiResponse<Map<String, String>> apiExceptionResponse = generateExceptionResponse(errorCode, ex.getErrors());

        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiExceptionResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex) {
        BaseErrorCode errorCode = GlobalErrorCode.METHOD_NOT_SUPPORTED;
        ApiResponse<Map<String, String>> apiExceptionResponse = generateExceptionResponse(errorCode, Map.of());
        String errorMessage = "Phương thức không được hỗ trợ cho yêu cầu này. " + "Phương thức hiện tại: "
                + ex.getMethod() + ". " + "Các phương thức hỗ trợ: " + ex.getSupportedHttpMethods();
        apiExceptionResponse.setMessage(errorMessage);
        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiExceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleGeneralException(Exception ex) {
        BaseErrorCode errorCode = GlobalErrorCode.UNCATEGORIZED_EXCEPTION;
        ApiResponse<Map<String, String>> apiExceptionResponse = generateExceptionResponse(errorCode, Map.of());
        apiExceptionResponse.setMessage("Đã xảy ra lỗi không xác định: " + ex.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiExceptionResponse);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleNoHandlerFoundException() {
        BaseErrorCode errorCode = GlobalErrorCode.PATH_NOT_FOUND;
        ApiResponse<Map<String, String>> apiExceptionResponse = generateExceptionResponse(errorCode, Map.of());
        apiExceptionResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiExceptionResponse);
    }
}
