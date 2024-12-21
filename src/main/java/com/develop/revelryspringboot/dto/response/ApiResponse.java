package com.develop.revelryspringboot.dto.response;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    int code = 200;
    String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    T result;

    public ApiResponse() {
        this.code = Integer.parseInt(String.valueOf(OK.value()));
        this.message = OK.getReasonPhrase();
    }

    public static <T> ApiResponse<T> ok(T body) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("OK");
        response.setResult(body);
        return response;
    }

    public static <T> ApiResponse<T> created(T body) {
        ApiResponse<T> response = ok(body);
        response.setMessage("CREATED");
        return response;
    }

    public static <T> ApiResponse<T> updated(T body) {
        ApiResponse<T> response = ok(body);
        response.setMessage("UPDATED");
        return response;
    }

    public static ApiResponse<Void> noContent() {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("NO CONTENT");
        return response;
    }

    public static ApiResponse<Void> deleted() {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("DELETED");
        return response;
    }

    public static <T> ResponseEntity<ApiResponse<T>> okEntity(T body) {
        return ResponseEntity.ok(ok(body));
    }
}
