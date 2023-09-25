package com.playground.productservice.application.port.in.usecase.dto;

public record GetProductCategoryListCommand(
        Long fromProductCategoryId,
        Long toProductCategoryId,
        String productCategoryName
) {

}
