package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiErrorExceptionsExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.productservice.application.port.in.usecase.UpdateProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryCommand;
import com.playground.productservice.infrastructure.in.rest.docs.UpdateProductCategoryExceptionDocs;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductCategoryResponse;
import com.playground.productservice.util.mapper.UpdateProductCategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class UpdateProductCategoryRestAdapter {

    private final UpdateProductCategoryUseCase updateProductCategoryUseCase;

    private final UpdateProductCategoryMapper mapper;

    @Operation(summary = "상품 카테고리 수정.")
    @Tag(name = "1-4. [상품 카테고리 수정]")
    @ApiErrorExceptionsExample(UpdateProductCategoryExceptionDocs.class)
    @PatchMapping("/v1/product/category/{productCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateProductCategoryResponse updateProductCategory(
        @Parameter(description = "상품 카테고리 ID.", example = "1") @PathVariable("productCategoryId") final Long productCategoryId,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "상품 카테고리 수정 정보.") @RequestBody(required = true) @Valid final UpdateProductCategoryRequest request
    ) {
        return mapper.infoToResponse(updateProductCategoryUseCase.execute(new UpdateProductCategoryCommand(productCategoryId, request.name())));
    }

}
