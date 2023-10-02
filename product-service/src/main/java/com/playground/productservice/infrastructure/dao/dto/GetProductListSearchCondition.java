package com.playground.productservice.infrastructure.dao.dto;

import java.math.BigDecimal;

public record GetProductListSearchCondition(
    String productName,
    BigDecimal minPrice,
    BigDecimal maxPrice,
    String productCategoryName
) {

}
