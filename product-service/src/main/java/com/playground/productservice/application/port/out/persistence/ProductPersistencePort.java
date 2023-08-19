package com.playground.productservice.application.port.out.persistence;

import com.playground.core.domain.product.ProductCategory;

import java.util.Optional;

public interface ProductPersistencePort {

    boolean isExistsProductCategoryByName(String name);

    Optional<ProductCategory> getProductCategoryById(Long productCategoryId);

    ProductCategory saveProductCategory(ProductCategory productCategory);

}
