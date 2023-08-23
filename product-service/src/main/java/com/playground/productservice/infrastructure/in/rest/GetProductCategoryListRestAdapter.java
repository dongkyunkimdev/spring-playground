package com.playground.productservice.infrastructure.in.rest;

import com.playground.productservice.application.port.in.usecase.GetProductCategoryListUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListCommand;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryResponse;
import com.playground.productservice.util.mapper.GetProductCategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetProductCategoryListRestAdapter {

    private final GetProductCategoryListUseCase getProductCategoryListUseCase;

    private final GetProductCategoryMapper mapper;

    @Operation(summary = "상품 카테고리 리스트 조회.")
    @GetMapping("/v1/product/category")
    @ResponseStatus(HttpStatus.OK)
    public Slice<GetProductCategoryResponse> getProductCategory(
            @Parameter(required = false, description = "상품 카테고리 시작 ID.") @RequestParam(required = false) final Long fromProductCategoryId,
            @Parameter(required = false, description = "상품 카테고리 종료 ID.") @RequestParam(required = false) final Long toProductCategoryId,
            @Parameter(required = false, description = "상품 카테고리 이름.") @RequestParam(required = false) final String productCategoryName,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        Slice<GetProductCategoryInfo> productCategoryInfoSlice = getProductCategoryListUseCase.execute(new GetProductCategoryListCommand(fromProductCategoryId, toProductCategoryId, productCategoryName), pageable);

        return productCategoryInfoSlice.map(mapper::infoToResponse);
    }

}
