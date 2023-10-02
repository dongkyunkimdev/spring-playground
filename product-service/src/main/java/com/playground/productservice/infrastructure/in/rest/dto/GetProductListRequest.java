package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record GetProductListRequest(

    @Schema(description = "상품 이름", example = "shirts") String productName,

    @PositiveOrZero(message = "minPrice는 음수일 수 없습니다.")
    @Schema(description = "상품 가격 최소값", example = "100") BigDecimal minPrice,

    @PositiveOrZero(message = "maxPrice는 음수일 수 없습니다.")
    @Schema(description = "상품 가격 최대값", example = "10000") BigDecimal maxPrice,

    @Schema(description = "상품 카테고리 이름", example = "Clothing") String productCategoryName
) {

}
