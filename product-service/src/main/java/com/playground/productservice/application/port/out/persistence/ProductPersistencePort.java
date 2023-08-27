package com.playground.productservice.application.port.out.persistence;

import com.playground.productservice.domain.ProductCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface ProductPersistencePort {

    boolean isExistsProductCategoryByName(String name);

    Optional<ProductCategory> findProductCategoryById(Long productCategoryId);

    Slice<ProductCategory> findProductCategoryListByIdRangeAndName(Long fromProductCategoryId, Long toProductCategoryId, String productCategoryName, Pageable pageable);

    ProductCategory saveProductCategory(ProductCategory productCategory);

    void deleteProductCategory(ProductCategory savedProductCategory);

}
