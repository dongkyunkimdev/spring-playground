package com.playground.productservice.support;

import com.playground.core.exception.dto.ErrorResponse;
import com.playground.productservice.domain.exception.ProductErrorCode;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertUtil {

    public static void assertErrorResponse(ErrorResponse responseDto, ProductErrorCode errorCode) {
        assertThat(responseDto.isSuccess()).isFalse();
        assertThat(responseDto.getStatus()).isEqualTo(errorCode.getStatus());
        assertThat(responseDto.getCode()).isEqualTo(errorCode.getCode());
        assertThat(responseDto.getReason()).isEqualTo(errorCode.getReason());
    }

}
