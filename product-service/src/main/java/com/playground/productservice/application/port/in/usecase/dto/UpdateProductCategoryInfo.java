package com.playground.productservice.application.port.in.usecase.dto;

import java.time.LocalDateTime;

public record UpdateProductCategoryInfo(
    Long productCategoryId,
    String name,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
