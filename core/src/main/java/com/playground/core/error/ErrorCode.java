package com.playground.core.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// TODO: Get All ErrorCode API 작성하여 클라에서 고유 에러코드를 조회할 수 있도록 함
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "C001", "Bad Request"),
    METHOD_ARGUMENT_TYPE_ENUM_BINDING_MISMATCH(HttpStatus.BAD_REQUEST.value(), "C002", "Bad Request (Method Argument Type Mismatch Error)"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "C003", "Unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "C004", "Forbidden"),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "C005", "Not Found"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "C006", "Method Not Allowed"),
    CONFLICT(HttpStatus.CONFLICT.value(), "C007", "Conflict"),
    PRECONDITION_FAILED(HttpStatus.PRECONDITION_FAILED.value(), "C008", "Precondition Failed"),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS.value(), "C009", "Too Many Requests"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "C010", "Internal Server Error"),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY.value(), "C011", "Bad Gateway"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(), "C012", "Service Unavailable"),
    INVALID_INPUT_VALUE_BINDING_ERROR(HttpStatus.BAD_REQUEST.value(), "C013", "Invalid Input Value Binding"),


    // User

    // Product
    PRODUCT_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "P001", "Product Category Not Found"),
    PRODUCT_CATEGORY_NAME_DUPLICATED(HttpStatus.CONFLICT.value(), "P002", "Product Category Name Duplicated");

    // Order

    // Delivery

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
