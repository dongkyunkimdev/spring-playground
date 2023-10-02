package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.RestAdapter;
import com.playground.core.paging.SliceResponse;
import com.playground.productservice.application.port.in.usecase.GetProductCategoryListUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListInfo;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryListRequest;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryListResponse;
import com.playground.productservice.util.mapper.GetProductCategoryListMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class GetProductCategoryListRestAdapter {

    private final GetProductCategoryListUseCase useCase;

    private final GetProductCategoryListMapper mapper;

    @Operation(summary = "상품 카테고리 리스트 조회.")
    @Tag(name = "1-1. [상품 카테고리 리스트 조회]")
    @GetMapping("/v1/products/categories")
    @ResponseStatus(HttpStatus.OK)
    public SliceResponse<GetProductCategoryListResponse> getProductCategoryList(
        @ParameterObject GetProductCategoryListRequest request,
        @ParameterObject @PageableDefault(size = 10, sort = "productCategoryId", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Slice<GetProductCategoryListInfo> getProductCategoryListInfoSlice = useCase.execute(mapper.requestToCommand(request), pageable);

        return SliceResponse.of(getProductCategoryListInfoSlice.map(mapper::infoToResponse));
    }

}
