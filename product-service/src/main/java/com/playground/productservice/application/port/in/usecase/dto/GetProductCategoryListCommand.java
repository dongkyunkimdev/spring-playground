package com.playground.productservice.application.port.in.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductCategoryListCommand {

    private Long fromProductCategoryId;

    private Long toProductCategoryId;

    private String productCategoryName;

}
