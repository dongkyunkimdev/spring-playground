package com.playground.productservice.application.port.in.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonProductCategoryInfo {

    private Long productCategoryId;

    private String name;

}
