package com.playground.productservice.infrastructure.dao;

import com.playground.core.paging.QueryDslUtil;
import com.playground.core.paging.SliceUtil;
import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.QProduct;
import com.playground.productservice.domain.QProductCategory;
import com.playground.productservice.infrastructure.dao.dto.GetProductListSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public Slice<Product> findListBySearchCondition(GetProductListSearchCondition searchCondition, Pageable pageable) {
        List<Product> content = queryFactory.selectFrom(QProduct.product)
            .where(
                productIdInRange(searchCondition.fromProductId(), searchCondition.toProductId()),
                productNameContains(searchCondition.productName()),
                priceInRange(searchCondition.minPrice(), searchCondition.maxPrice()),
                productCategoryIdInRange(searchCondition.fromProductCategoryId(), searchCondition.toProductCategoryId()),
                productCategoryNameContains(searchCondition.productCategoryName())
            )
            .orderBy(QueryDslUtil.createOrderSpecifiers(Product.class, pageable))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        return SliceUtil.createSlice(content, pageable);
    }

    private BooleanExpression productIdInRange(Long fromProductId, Long toProductId) {
        if (fromProductId == null && toProductId == null) {
            return null;
        }

        if (fromProductId == null) {
            return QProduct.product.productId.loe(toProductId);
        }

        if (toProductId == null) {
            return QProduct.product.productId.goe(fromProductId);
        }

        return QProduct.product.productId.between(fromProductId, toProductId);
    }

    private BooleanExpression productNameContains(String productName) {
        return StringUtils.isBlank(productName) ? null : QProduct.product.name.containsIgnoreCase(productName);
    }

    private BooleanExpression priceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return null;
        }

        if (minPrice == null) {
            return QProduct.product.productId.loe(maxPrice);
        }

        if (maxPrice == null) {
            return QProduct.product.productId.goe(minPrice);
        }

        return QProduct.product.price.between(minPrice, maxPrice);
    }

    private BooleanExpression productCategoryIdInRange(Long fromProductCategoryId, Long toProductCategoryId) {
        if (fromProductCategoryId == null && toProductCategoryId == null) {
            return null;
        }

        if (fromProductCategoryId == null) {
            return QProductCategory.productCategory.productCategoryId.loe(toProductCategoryId);
        }

        if (toProductCategoryId == null) {
            return QProductCategory.productCategory.productCategoryId.goe(fromProductCategoryId);
        }

        return QProductCategory.productCategory.productCategoryId.between(fromProductCategoryId, toProductCategoryId);
    }

    private BooleanExpression productCategoryNameContains(String productCategoryName) {
        return StringUtils.isBlank(productCategoryName) ? null : QProductCategory.productCategory.name.containsIgnoreCase(productCategoryName);
    }

}
