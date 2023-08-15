package com.playground.productservice.application.port.out.persistence;

import com.playground.core.domain.product.ProductCategory;

public interface ProductPersistencePort {

    boolean isExistsProductCategoryByName(String name);

    ProductCategory saveProductCategory(ProductCategory productCategory);

}
