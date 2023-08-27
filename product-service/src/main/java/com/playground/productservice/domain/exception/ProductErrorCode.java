package com.playground.productservice.domain.exception;

import com.playground.core.exception.dto.ErrorReason;
import com.playground.core.exception.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductErrorCode implements BaseErrorCode {

    PRODUCT_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "PRODUCT_001", "존재하지 않는 상품 카테고리입니다."),
    PRODUCT_CATEGORY_NAME_DUPLICATED(HttpStatus.CONFLICT.value(), "PRODUCT_002", "중복된 상품 카테고리 이름입니다.");

    private final int status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().status(status).code(code).reason(reason).build();
    }

}
