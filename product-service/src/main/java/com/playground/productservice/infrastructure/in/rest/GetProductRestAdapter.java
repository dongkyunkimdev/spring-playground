package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiExceptionExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.productservice.application.port.in.usecase.GetProductUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCommand;
import com.playground.productservice.infrastructure.in.rest.docs.GetProductExceptionDocs;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductResponse;
import com.playground.productservice.util.mapper.GetProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class GetProductRestAdapter {

    private final GetProductUseCase useCase;

    private final GetProductMapper mapper;

    @Operation(summary = "상품 조회.")
    @Tag(name = "2-2. [상품 조회]")
    @ApiExceptionExample(GetProductExceptionDocs.class)
    @GetMapping(value = "/v1/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GetProductResponse getProduct(
        @Parameter(description = "상품 ID.", example = "1") @PathVariable("productId") final Long productId
    ) {
        return mapper.infoToResponse(useCase.execute(new GetProductCommand(productId)));
    }

}
