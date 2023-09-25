package com.playground.productservice.application.port.in.usecase.dto;

public record GetProductCategoryInfo(
        Long productCategoryId,
        String name
) {

}
