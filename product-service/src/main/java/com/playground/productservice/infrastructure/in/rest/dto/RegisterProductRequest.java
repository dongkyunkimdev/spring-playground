package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductRequest {

    @NotBlank
    @Schema(description = "이름", example = "Black T-Shirts")
    private String name;

    @NotNull
    @Schema(description = "재고", example = "100")
    private Long stock;

    @NotNull
    @Schema(description = "가격", example = "1000")
    private BigDecimal price;

    @NotNull
    @Schema(description = "상품 카테고리 ID", example = "1")
    private Long productCategoryId;

}
