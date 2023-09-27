package com.playground.productservice.application.port.in.usecase.dto;

import java.math.BigDecimal;

public record UpdateProductCommand(
        Long productId,
        String name,
        Long stock,
        BigDecimal price,
        Long productCategoryId
) {

}
