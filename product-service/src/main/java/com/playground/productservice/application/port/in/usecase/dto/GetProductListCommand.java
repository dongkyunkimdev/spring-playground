package com.playground.productservice.application.port.in.usecase.dto;

import java.math.BigDecimal;

public record GetProductListCommand(
    String productName,
    BigDecimal minPrice,
    BigDecimal maxPrice,
    String productCategoryName
) {

}
