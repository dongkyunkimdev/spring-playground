package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetProductCategoryListRequest(
        @Schema(description = "상품 카테고리 시작 ID", example = "1") Long fromProductCategoryId,
        @Schema(description = "상품 카테고리 종료 ID", example = "10") Long toProductCategoryId,
        @Schema(description = "상품 카테고리 이름.", example = "Clothing") String productCategoryName
) {

}
