package com.playground.productservice.infrastructure.out.persistence;

import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.infrastructure.dao.ProductCategoryRepository;
import com.playground.productservice.infrastructure.dao.ProductCategoryRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductCategoryRepository productCategoryRepository;

    private final ProductCategoryRepositorySupport productCategoryRepositorySupport;

    @Override
    public boolean isExistsProductCategoryByName(String name) {
        return productCategoryRepository.existsByName(name);
    }

    @Override
    public Optional<ProductCategory> findProductCategoryById(Long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId);
    }

    @Override
    public Slice<ProductCategory> findProductCategoryListByIdRangeAndName(Long fromProductCategoryId, Long toProductCategoryId, String productCategoryName, Pageable pageable) {
        return productCategoryRepositorySupport.findAllByIdRangeAndName(fromProductCategoryId, toProductCategoryId, productCategoryName, pageable);
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

}
