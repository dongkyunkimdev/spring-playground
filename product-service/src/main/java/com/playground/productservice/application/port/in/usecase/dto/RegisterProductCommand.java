package com.playground.productservice.application.port.in.usecase.dto;

import java.math.BigDecimal;

public record RegisterProductCommand(
    String name,
    Long stock,
    BigDecimal price,
    Long productCategoryId
) {

}
