package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.annotation.RestAdapter;
import com.playground.core.paging.SliceResponse;
import com.playground.productservice.application.port.in.usecase.GetProductListUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductListInfo;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductListRequest;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductListResponse;
import com.playground.productservice.util.mapper.GetProductListMapper;
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
public class GetProductListRestAdapter {

    private final GetProductListUseCase getProductListUseCase;

    private final GetProductListMapper mapper;

    @Operation(summary = "상품 리스트 조회.")
    @Tag(name = "2-1. [상품 리스트 조회]")
    @GetMapping("/v1/products")
    @ResponseStatus(HttpStatus.OK)
    public SliceResponse<GetProductListResponse> getProductList(
        @ParameterObject GetProductListRequest request,
        @ParameterObject @PageableDefault(size = 10, sort = "productId", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Slice<GetProductListInfo> getProductListInfoSlice = getProductListUseCase.execute(mapper.requestToCommand(request), pageable);

        return SliceResponse.of(getProductListInfoSlice.map(mapper::infoToResponse));
    }

}
