package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record CommonProductCategoryResponse(
    @Schema(description = "상품 카테고리 ID", example = "1") Long productCategoryId,
    @Schema(description = "상품 카테고리 이름", example = "Clothing") String name,
    @Schema(description = "생성 일시", example = "2023-10-01T00:00:00") LocalDateTime createdAt,
    @Schema(description = "수정 일시", example = "2023-10-02T00:00:00") LocalDateTime updatedAt
) {

}
