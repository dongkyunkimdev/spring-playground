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
public class GetProductCategoryListRequest {

    @Schema(description = "상품 카테고리 시작 ID", example = "1")
    private Long fromProductCategoryId;

    @Schema(description = "상품 카테고리 종료 ID", example = "10")
    private Long toProductCategoryId;

    @Schema(description = "상품 카테고리 이름.", example = "Clothing")
    private String productCategoryName;

}
