package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RegisterProductRequest(
        @NotBlank @Schema(description = "이름", example = "Black T-Shirts") String name,
        @NotNull @Schema(description = "재고", example = "100") Long stock,
        @NotNull @Schema(description = "가격", example = "1000") BigDecimal price,
        @NotNull @Schema(description = "상품 카테고리 ID", example = "1") Long productCategoryId
) {

}
