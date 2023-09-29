package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record GetProductResponse(
    @Schema(description = "상품 ID", example = "1") Long productId,
    @Schema(description = "상품 이름", example = "T-Shirts") String name,
    @Schema(description = "재고", example = "100") Long stock,
    @Schema(description = "가격", example = "10000") BigDecimal price,
    CommonProductCategoryResponse productCategory
) {

}
