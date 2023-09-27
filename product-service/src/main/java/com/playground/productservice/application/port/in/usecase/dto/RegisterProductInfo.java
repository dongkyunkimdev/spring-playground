package com.playground.productservice.application.port.in.usecase.dto;

import java.math.BigDecimal;

public record RegisterProductInfo(
    Long productId,
    String name,
    Long stock,
    BigDecimal price,
    CommonProductCategoryInfo productCategory
) {

}
