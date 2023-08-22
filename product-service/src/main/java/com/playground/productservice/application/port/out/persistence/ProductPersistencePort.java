package com.playground.productservice.application.port.out.persistence;

import com.playground.core.domain.product.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductPersistencePort {

    boolean isExistsProductCategoryByName(String name);

    Optional<ProductCategory> findProductCategoryById(Long productCategoryId);

    List<ProductCategory> findProductCategoryListByIdRangeAndName(Long fromProductCategoryId, Long toProductCategoryId, String productCategoryName);

    ProductCategory saveProductCategory(ProductCategory productCategory);

}
