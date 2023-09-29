package com.playground.productservice.application.port.out.persistence;

import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.dao.dto.GetProductCategoryListSearchCondition;
import com.playground.productservice.infrastructure.dao.dto.GetProductListSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface ProductPersistencePort {

    Slice<Product> searchProductListBySearchCondition(GetProductListSearchCondition getProductListSearchCondition, Pageable pageable);

    Slice<ProductCategory> searchProductCategoryListBySearchCondition(GetProductCategoryListSearchCondition searchCondition, Pageable pageable);

    Optional<Product> searchProductById(Long productId);

    Optional<ProductCategory> searchProductCategoryById(Long productCategoryId);

    boolean isExistsProductCategoryByName(String name);

    Product saveProduct(Product product);

    ProductCategory saveProductCategory(ProductCategory productCategory);

    void deleteProduct(Product savedProduct);

    void deleteProductCategory(ProductCategory savedProductCategory);

}
