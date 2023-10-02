package com.playground.productservice.application.port.in.usecase.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateProductInfo(
    Long productId,
    String name,
    Long stock,
    BigDecimal price,
    CommonProductCategoryInfo productCategory,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
