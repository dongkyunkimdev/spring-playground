package com.playground.productservice.infrastructure.in.rest;

import com.playground.productservice.application.port.in.usecase.RegisterProductCategoryUseCase;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryResponse;
import com.playground.productservice.util.mapper.RegisterProductCategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterProductCategoryRestAdapter {

    private final RegisterProductCategoryUseCase registerProductCategoryUseCase;

    private final RegisterProductCategoryMapper mapper;

    @PostMapping("/v1/product/category")
    public RegisterProductCategoryResponse registerProductCategory(@RequestBody @Valid RegisterProductCategoryRequest request) {
        return mapper.infoToResponse(registerProductCategoryUseCase.execute(mapper.requestToCommand(request)));
    }

}
