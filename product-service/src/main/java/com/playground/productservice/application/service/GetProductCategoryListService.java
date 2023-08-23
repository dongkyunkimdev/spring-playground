package com.playground.productservice.application.service;

import com.playground.core.domain.product.ProductCategory;
import com.playground.productservice.application.port.in.usecase.GetProductCategoryListUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListCommand;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.util.mapper.GetProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductCategoryListService implements GetProductCategoryListUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final GetProductCategoryMapper mapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<GetProductCategoryInfo> execute(GetProductCategoryListCommand command) {
        List<ProductCategory> productCategoryList = productPersistencePort.findProductCategoryListByIdRangeAndName(command.getFromProductCategoryId(), command.getToProductCategoryId(), command.getProductCategoryName());

        return productCategoryList.stream().map(mapper::entityToInfo).toList();
    }

}
