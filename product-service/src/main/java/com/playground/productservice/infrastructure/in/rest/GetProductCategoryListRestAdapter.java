package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.RestAdapter;
import com.playground.core.paging.SliceResponse;
import com.playground.productservice.application.port.in.usecase.GetProductCategoryListUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListCommand;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryListRequest;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryResponse;
import com.playground.productservice.util.mapper.GetProductCategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class GetProductCategoryListRestAdapter {

    private final GetProductCategoryListUseCase getProductCategoryListUseCase;

    private final GetProductCategoryMapper mapper;

    @Operation(summary = "상품 카테고리 리스트 조회.")
    @Tag(name = "1-1. [상품 카테고리 리스트 조회]")
    @GetMapping("/v1/product/category")
    @ResponseStatus(HttpStatus.OK)
    public SliceResponse<GetProductCategoryResponse> getProductCategory(
            @ParameterObject GetProductCategoryListRequest request,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        Slice<GetProductCategoryInfo> getProductCategoryInfoSlice = getProductCategoryListUseCase.execute(new GetProductCategoryListCommand(request.getFromProductCategoryId(), request.getToProductCategoryId(), request.getProductCategoryName()), pageable);

        return SliceResponse.of(getProductCategoryInfoSlice.map(mapper::infoToResponse));
    }

}
