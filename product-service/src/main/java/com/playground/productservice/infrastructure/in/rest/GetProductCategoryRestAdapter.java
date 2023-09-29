package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiErrorExceptionsExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.productservice.application.port.in.usecase.GetProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryCommand;
import com.playground.productservice.infrastructure.in.rest.docs.GetProductCategoryExceptionDocs;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryResponse;
import com.playground.productservice.util.mapper.GetProductCategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class GetProductCategoryRestAdapter {

    private final GetProductCategoryUseCase getProductCategoryUseCase;

    private final GetProductCategoryMapper mapper;

    @Operation(summary = "상품 카테고리 조회.")
    @Tag(name = "1-2. [상품 카테고리 조회]")
    @ApiErrorExceptionsExample(GetProductCategoryExceptionDocs.class)
    @GetMapping("/v1/products/categories/{productCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductCategoryResponse getProductCategory(
        @Parameter(description = "상품 카테고리 ID.", example = "1") @PathVariable("productCategoryId") final Long productCategoryId
    ) {
        return mapper.infoToResponse(getProductCategoryUseCase.execute(new GetProductCategoryCommand(productCategoryId)));
    }

}
