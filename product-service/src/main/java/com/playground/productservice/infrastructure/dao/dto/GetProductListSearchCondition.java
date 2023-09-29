package com.playground.productservice.infrastructure.dao.dto;

import java.math.BigDecimal;

public record GetProductListSearchCondition(
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
