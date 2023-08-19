package com.playground.productservice.infrastructure.in.rest;

import com.playground.productservice.application.port.in.usecase.GetProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryCommand;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryResponse;
import com.playground.productservice.util.mapper.GetProductCategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetProductCategoryRestAdapter {

    private final GetProductCategoryUseCase getProductCategoryUseCase;

    private final GetProductCategoryMapper mapper;

    @Operation(summary = "상품 카테고리 조회")
    @GetMapping("/v1/product/category/{productCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductCategoryResponse getProductCategory(
            @Parameter(required = true, description = "상품 카테고리 ID.") @PathVariable("productCategoryId") final Long productCategoryId
    ) {
        return mapper.infoToResponse(getProductCategoryUseCase.execute(new GetProductCategoryCommand(productCategoryId)));
    }

}