package com.playground.productservice.infrastructure.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductCategoryListRequest {

    private Long fromProductCategoryId;

    private Long toProductCategoryId;

    private String productCategoryName;

}
