package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetProductCategoryListRequest(
    @Schema(description = "상품 카테고리 이름.", example = "Clothing") String productCategoryName
) {

}
