package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiErrorExceptionsExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.productservice.application.port.in.usecase.RegisterProductCategoryUseCase;
import com.playground.productservice.infrastructure.in.rest.docs.RegisterProductCategoryExceptionDocs;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryResponse;
import com.playground.productservice.util.mapper.RegisterProductCategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class RegisterProductCategoryRestAdapter {

    private final RegisterProductCategoryUseCase registerProductCategoryUseCase;

    private final RegisterProductCategoryMapper mapper;

    @Operation(summary = "상품 카테고리 등록.")
    @Tag(name = "2-1. [상품 카테고리 등록]")
    @ApiErrorExceptionsExample(RegisterProductCategoryExceptionDocs.class)
    @PostMapping("/v1/product/category")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterProductCategoryResponse registerProductCategory(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "상품 카테고리 등록 정보.") @RequestBody(required = true) @Valid final RegisterProductCategoryRequest request
    ) {
        return mapper.infoToResponse(registerProductCategoryUseCase.execute(mapper.requestToCommand(request)));
    }

}
