package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.GetProductCategoryListUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.util.mapper.GetProductCategoryListMapper;
import com.playground.productservice.util.mapper.GetProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetProductCategoryListService implements GetProductCategoryListUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final GetProductCategoryListMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Slice<GetProductCategoryListInfo> execute(GetProductCategoryListCommand command, Pageable pageable) {
        Slice<ProductCategory> productCategorySlice = productPersistencePort.findProductCategoryListByIdRangeAndName(command.fromProductCategoryId(), command.toProductCategoryId(), command.productCategoryName(), pageable);

        return productCategorySlice.map(mapper::entityToInfo);
    }

}
