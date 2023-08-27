package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductCategoryResponse {

    @Schema(description = "상품 카테고리 ID", example = "1")
    private Long productCategoryId;

    @Schema(description = "상품 카테고리명", example = "Clothing")
    private String name;

}
