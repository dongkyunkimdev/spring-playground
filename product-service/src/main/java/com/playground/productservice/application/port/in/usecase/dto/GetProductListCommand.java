package com.playground.productservice.application.port.in.usecase.dto;

import java.math.BigDecimal;

public record GetProductListCommand(
    Long fromProductId,
    Long toProductId,
    String productName,
    BigDecimal minPrice,
    BigDecimal maxPrice,
    Long fromProductCategoryId,
    Long toProductCategoryId,
    String productCategoryName
) {

}
