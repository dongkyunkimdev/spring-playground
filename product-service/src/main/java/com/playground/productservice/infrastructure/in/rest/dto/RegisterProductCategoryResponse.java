package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterProductCategoryResponse(
    @Schema(description = "상품 카테고리 ID", example = "1") Long productCategoryId,
    @Schema(description = "상품 카테고리 이름", example = "Clothing") String name
) {

}
