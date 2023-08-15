package com.playground.productservice.infrastructure.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterProductCategoryResponse {

    private Long id;

    private String name;

}
