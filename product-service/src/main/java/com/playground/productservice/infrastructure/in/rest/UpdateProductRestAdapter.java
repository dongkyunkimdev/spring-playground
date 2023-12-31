package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiExceptionExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.productservice.application.port.in.usecase.UpdateProductUseCase;
import com.playground.productservice.infrastructure.in.rest.docs.UpdateProductExceptionDocs;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductRequest;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductResponse;
import com.playground.productservice.util.mapper.UpdateProductMapper;
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
public class UpdateProductRestAdapter {

    private final UpdateProductUseCase useCase;

    private final UpdateProductMapper mapper;

    @Operation(summary = "상품 수정.")
    @Tag(name = "2-4. [상품 수정]")
    @ApiExceptionExample(UpdateProductExceptionDocs.class)
    @PatchMapping(value = "/v1/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UpdateProductResponse updateProduct(
        @Parameter(description = "상품 ID.", example = "1") @PathVariable("productId") final Long productId,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "상품 수정 정보.") @RequestBody(required = true) @Valid final UpdateProductRequest request
    ) {
        return mapper.infoToResponse(useCase.execute(mapper.requestToEntity(productId, request)));
    }

}
