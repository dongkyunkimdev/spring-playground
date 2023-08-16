package com.playground.productservice.application.port.in.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterProductCategoryInfo {

    private Long id;

    private String name;

}