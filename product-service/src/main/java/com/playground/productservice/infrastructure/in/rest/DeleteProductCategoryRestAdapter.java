package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiErrorExceptionsExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.productservice.application.port.in.usecase.DeleteProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.DeleteProductCategoryCommand;
import com.playground.productservice.infrastructure.in.rest.docs.DeleteProductCategoryExceptionDocs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class DeleteProductCategoryRestAdapter {

    private final DeleteProductCategoryUseCase deleteProductCategoryUseCase;

    @Operation(summary = "상품 카테고리 삭제.")
    @Tag(name = "1-5. [상품 카테고리 삭제]")
    @ApiErrorExceptionsExample(DeleteProductCategoryExceptionDocs.class)
    @DeleteMapping("/v1/product/category/{productCategoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductCategory(
            @Parameter(description = "상품 카테고리 ID.", example = "1") @PathVariable("productCategoryId") final Long productCategoryId
    ) {
        deleteProductCategoryUseCase.execute(new DeleteProductCategoryCommand(productCategoryId));
    }

}
