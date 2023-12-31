package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiExceptionExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.productservice.application.port.in.usecase.UpdateProductCategoryUseCase;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class UpdateProductCategoryRestAdapter {

    private final UpdateProductCategoryUseCase useCase;

    private final UpdateProductCategoryMapper mapper;

    @Operation(summary = "상품 카테고리 수정.")
    @Tag(name = "1-4. [상품 카테고리 수정]")
    @ApiExceptionExample(UpdateProductCategoryExceptionDocs.class)
    @PatchMapping(value = "/v1/products/categories/{productCategoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UpdateProductCategoryResponse updateProductCategory(
        @Parameter(description = "상품 카테고리 ID.", example = "1") @PathVariable("productCategoryId") final Long productCategoryId,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "상품 카테고리 수정 정보.") @RequestBody(required = true) @Valid final UpdateProductCategoryRequest request
    ) {
        return mapper.infoToResponse(useCase.execute(mapper.requestToCommand(productCategoryId, request)));
    }

}
