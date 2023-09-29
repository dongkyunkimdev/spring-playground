package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

public record GetProductCategoryListRequest(
    @Positive(message = "fromProductCategoryId는 양수여야만 합니다.")
    @Schema(description = "상품 카테고리 시작 ID", example = "1") Long fromProductCategoryId,

    @Positive(message = "toProductCategoryId는 양수여야만 합니다.")
    @Schema(description = "상품 카테고리 종료 ID", example = "10") Long toProductCategoryId,

    @Schema(description = "상품 카테고리 이름.", example = "Clothing") String productCategoryName
) {

}
