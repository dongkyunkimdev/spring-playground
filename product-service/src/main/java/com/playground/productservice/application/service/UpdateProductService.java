package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.UpdateProductUseCase;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCommand;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.exception.ProductCategoryNotFoundException;
import com.playground.productservice.domain.exception.ProductNotFoundException;
import com.playground.productservice.util.mapper.UpdateProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final UpdateProductMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public UpdateProductInfo execute(UpdateProductCommand command) {
        Product savedProduct = getProduct(command.productId());
        ProductCategory savedProductCategory = getProductCategory(command.productCategoryId());

        savedProduct.update(command, savedProductCategory);

        return mapper.entityToInfo(savedProduct);
    }

    private Product getProduct(Long productId) {
        return productPersistencePort.searchProductById(productId)
            .orElseThrow(ProductNotFoundException::new);
    }

    private ProductCategory getProductCategory(Long productCategoryId) {
        return productPersistencePort.searchProductCategoryById(productCategoryId)
            .orElseThrow(ProductCategoryNotFoundException::new);
    }

}
