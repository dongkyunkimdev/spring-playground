package com.playground.productservice.infrastructure.in.rest.dto;

import java.math.BigDecimal;

public record UpdateProductResponse(
    Long productId,
    String name,
    Long stock,
    BigDecimal price,
    CommonProductCategoryResponse productCategory
) {

}
