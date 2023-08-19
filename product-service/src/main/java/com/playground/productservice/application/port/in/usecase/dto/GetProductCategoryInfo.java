package com.playground.productservice.application.port.in.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetProductCategoryInfo {

    private Long productCategoryId;

    private String name;

}
