package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record GetProductListRequest(
    @Positive(message = "fromProductId는 양수여야만 합니다.")
    @Schema(description = "상품 시작 ID", example = "1") Long fromProductId,

    @Positive(message = "toProductId는 양수여야만 합니다.")
    @Schema(description = "상품 시작 ID", example = "10") Long toProductId,

    @Schema(description = "상품 이름", example = "shirts") String productName,

    @PositiveOrZero(message = "minPrice는 음수일 수 없습니다.")
    @Schema(description = "상품 가격 최소값", example = "100") BigDecimal minPrice,

    @PositiveOrZero(message = "maxPrice는 음수일 수 없습니다.")
    @Schema(description = "상품 가격 최대값", example = "10000") BigDecimal maxPrice,

    @Positive(message = "fromProductCategoryId는 양수여야만 합니다.")
    @Schema(description = "상품 카테고리 시작 ID", example = "1") Long fromProductCategoryId,

    @Positive(message = "toProductCategoryId는 양수여야만 합니다.")
    @Schema(description = "상품 카테고리 종료 ID", example = "10") Long toProductCategoryId,

    @Schema(description = "상품 카테고리 이름", example = "Clothing") String productCategoryName
) {

}
