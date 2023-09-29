package com.playground.productservice.application.port.in.usecase.dto;

public record GetProductCategoryListInfo(
    Long productCategoryId,
    String name
) {

}
