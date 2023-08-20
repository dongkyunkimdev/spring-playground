package com.playground.productservice.application.service;

import com.playground.core.domain.product.ProductCategory;
import com.playground.core.domain.product.exception.ProductCategoryNotFoundException;
import com.playground.productservice.application.port.in.usecase.GetProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.util.mapper.GetProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetProductCategoryService implements GetProductCategoryUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final GetProductCategoryMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public GetProductCategoryInfo execute(GetProductCategoryCommand command) {
        ProductCategory productCategory = productPersistencePort.getProductCategoryById(command.getProductCategoryId())
                .orElseThrow(ProductCategoryNotFoundException::new);

        return mapper.entityToInfo(productCategory);
    }

}
