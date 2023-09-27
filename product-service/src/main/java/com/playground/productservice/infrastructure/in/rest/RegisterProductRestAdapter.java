package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiErrorExceptionsExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.productservice.application.port.in.usecase.RegisterProductUseCase;
import com.playground.productservice.infrastructure.in.rest.docs.RegisterProductExceptionDocs;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductResponse;
import com.playground.productservice.util.mapper.RegisterProductMapper;
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
public class RegisterProductRestAdapter {

    private final RegisterProductUseCase registerProductUseCase;

    private final RegisterProductMapper mapper;

    @Operation(summary = "상품 등록.")
    @Tag(name = "2-3. [상품 등록]")
    @ApiErrorExceptionsExample(RegisterProductExceptionDocs.class)
    @PostMapping("/v1/product")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterProductResponse registerProduct(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "상품 등록 정보.") @RequestBody(required = true) @Valid final RegisterProductRequest request
    ) {
        return mapper.infoToResponse(registerProductUseCase.execute(mapper.requestToCommand(request)));
    }

}
