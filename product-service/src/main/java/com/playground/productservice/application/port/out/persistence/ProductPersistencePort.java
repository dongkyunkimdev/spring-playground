package com.playground.productservice.application.port.out.persistence;

import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.ProductCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface ProductPersistencePort {

    Slice<ProductCategory> findProductCategoryListByIdRangeAndName(Long fromProductCategoryId, Long toProductCategoryId, String productCategoryName, Pageable pageable);

    Optional<Product> findProductById(Long productId);

    Optional<ProductCategory> findProductCategoryById(Long productCategoryId);

    boolean isExistsProductCategoryByName(String name);

    Product saveProduct(Product product);

    ProductCategory saveProductCategory(ProductCategory productCategory);

    void deleteProduct(Product savedProduct);

    void deleteProductCategory(ProductCategory savedProductCategory);

}
