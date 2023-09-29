package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UpdateProductRequest(
    @NotBlank(message = "name은 비어있을 수 없습니다.")
    @Schema(description = "이름", example = "Black T-Shirts") String name,

    @NotNull(message = "stock은 비어있을 수 없습니다.")
    @Schema(description = "재고", example = "100") Long stock,

    @NotNull(message = "price는 비어있을 수 없습니다.")
    @PositiveOrZero(message = "price는 음수일 수 없습니다.")
    @Schema(description = "가격", example = "1000") BigDecimal price,

    @NotNull(message = "productCategoryId는 비어있을 수 없습니다.")
    @Positive(message = "productCategoryId는 음수일 수 없습니다.")
    @Schema(description = "상품 카테고리 ID", example = "1") Long productCategoryId
) {

}
