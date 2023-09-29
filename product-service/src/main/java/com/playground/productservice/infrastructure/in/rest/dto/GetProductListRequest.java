package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record GetProductListRequest(
    @Schema(description = "상품 시작 ID", example = "1") Long fromProductId,
    @Schema(description = "상품 시작 ID", example = "10") Long toProductId,
    @Schema(description = "상품 이름", example = "shirts") String productName,
    @Schema(description = "상품 가격 최소값", example = "100") BigDecimal minPrice,
    @Schema(description = "상품 가격 최대값", example = "10000") BigDecimal maxPrice,
    @Schema(description = "상품 카테고리 시작 ID", example = "1") Long fromProductCategoryId,
    @Schema(description = "상품 카테고리 종료 ID", example = "10") Long toProductCategoryId,
    @Schema(description = "상품 카테고리 이름", example = "Clothing") String productCategoryName
) {

}
