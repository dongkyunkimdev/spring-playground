package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiErrorExceptionsExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.productservice.application.port.in.usecase.DeleteProductUseCase;
import com.playground.productservice.application.port.in.usecase.dto.DeleteProductCommand;
import com.playground.productservice.infrastructure.in.rest.docs.DeleteProductCategoryExceptionDocs;
import com.playground.productservice.infrastructure.in.rest.docs.DeleteProductExceptionDocs;
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
public class DeleteProductRestAdapter {

    private final DeleteProductUseCase deleteProductUseCase;

    @Operation(summary = "상품 삭제.")
    @Tag(name = "2-5. [상품 삭제]")
    @ApiErrorExceptionsExample(DeleteProductExceptionDocs.class)
    @DeleteMapping("/v1/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(
        @Parameter(description = "상품 ID.", example = "1") @PathVariable("productId") final Long productId
    ) {
        deleteProductUseCase.execute(new DeleteProductCommand(productId));
    }

}
