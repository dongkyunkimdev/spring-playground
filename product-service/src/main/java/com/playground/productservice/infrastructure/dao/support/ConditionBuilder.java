package com.playground.productservice.infrastructure.dao.support;

import com.playground.productservice.domain.QProduct;
import com.playground.productservice.domain.QProductCategory;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class ConditionBuilder {

    public static BooleanExpression productIdInRange(Long fromProductId, Long toProductId) {
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

    public static BooleanExpression productNameContains(String productName) {
        return StringUtils.isBlank(productName) ? null : QProduct.product.name.containsIgnoreCase(productName);
    }

    public static BooleanExpression priceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return null;
        }

        if (minPrice == null) {
            return QProduct.product.price.loe(maxPrice);
        }

        if (maxPrice == null) {
            return QProduct.product.price.goe(minPrice);
        }

        return QProduct.product.price.between(minPrice, maxPrice);
    }

    public static BooleanExpression productCategoryIdInRange(Long fromProductCategoryId, Long toProductCategoryId) {
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

    public static BooleanExpression productCategoryNameContains(String productCategoryName) {
        return StringUtils.isBlank(productCategoryName) ? null : QProductCategory.productCategory.name.containsIgnoreCase(productCategoryName);
    }

}
