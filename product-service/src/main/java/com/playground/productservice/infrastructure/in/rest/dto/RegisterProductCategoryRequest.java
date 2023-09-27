package com.playground.productservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record RegisterProductCategoryRequest(
    @NotBlank @Schema(description = "이름", example = "Clothing") String name
) {

}
