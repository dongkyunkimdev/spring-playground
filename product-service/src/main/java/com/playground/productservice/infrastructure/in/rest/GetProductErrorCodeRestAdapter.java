package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiErrorCodeExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.productservice.domain.exception.ProductErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class GetProductErrorCodeRestAdapter {

    @Operation(summary = "상품 서비스 전체 에러코드 조회.")
    @Tag(name = "99. [상품 서비스 전체 에러코드 조회]")
    @ApiErrorCodeExample(ProductErrorCode.class)
    @GetMapping("/v1/products/error-code")
    @ResponseStatus(HttpStatus.OK)
    public void getProductErrorCode() {
    }

}
