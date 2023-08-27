package com.playground.productservice.infrastructure.in.rest;

import com.playground.productservice.application.port.in.usecase.RegisterProductCategoryUseCase;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryResponse;
import com.playground.productservice.util.mapper.RegisterProductCategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterProductCategoryRestAdapter {

    private final RegisterProductCategoryUseCase registerProductCategoryUseCase;

    private final RegisterProductCategoryMapper mapper;

    @Operation(summary = "상품 카테고리 등록.")
    @PostMapping("/v1/product/category")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterProductCategoryResponse registerProductCategory(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "상품 카테고리 등록 정보.") @RequestBody(required = true) @Valid final RegisterProductCategoryRequest request
    ) {
        return mapper.infoToResponse(registerProductCategoryUseCase.execute(mapper.requestToCommand(request)));
    }

}
