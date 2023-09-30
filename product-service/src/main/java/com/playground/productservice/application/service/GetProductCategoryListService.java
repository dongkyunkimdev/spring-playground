package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.GetProductCategoryListUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.util.mapper.GetProductCategoryListMapper;
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
        Slice<ProductCategory> productCategorySlice = getProductCategorySlice(command, pageable);

        return productCategorySlice.map(mapper::entityToInfo);
    }

    private Slice<ProductCategory> getProductCategorySlice(GetProductCategoryListCommand command, Pageable pageable) {
        return productPersistencePort.searchProductCategoryListBySearchCondition(mapper.commandToSearchCondition(command), pageable);
    }

}
