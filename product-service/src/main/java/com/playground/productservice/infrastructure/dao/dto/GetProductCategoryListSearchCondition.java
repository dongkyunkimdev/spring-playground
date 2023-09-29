package com.playground.productservice.infrastructure.dao.dto;

public record GetProductCategoryListSearchCondition(
    Long fromProductCategoryId,
    Long toProductCategoryId,
    String productCategoryName
) {

}
