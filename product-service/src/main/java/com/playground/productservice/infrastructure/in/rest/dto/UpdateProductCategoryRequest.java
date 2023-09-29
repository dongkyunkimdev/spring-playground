package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UpdateProductCategoryRequest(
    @NotBlank(message = "name은 비어있을 수 없습니다.")
    @Schema(description = "이름", example = "Clothing") String name
) {

}
