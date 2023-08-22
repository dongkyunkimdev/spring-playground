package com.playground.productservice.infrastructure.dao;

import com.playground.core.domain.product.ProductCategory;
import com.playground.core.domain.product.QProductCategory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCategoryRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public List<ProductCategory> findAllByIdRangeAndName(Long fromProductCategoryId, Long toProductCategoryId, String productCategoryName) {
        return queryFactory.selectFrom(QProductCategory.productCategory)
                .where(
                        productCategoryIdInRange(fromProductCategoryId, toProductCategoryId),
                        nameContains(productCategoryName)
                )
                .fetch();
    }

    private BooleanExpression productCategoryIdInRange(Long from, Long to) {
        if (from == null && to == null) {
            return null;
        }

        if (from == null) {
            return QProductCategory.productCategory.productCategoryId.loe(to);
        }

        if (to == null) {
            return QProductCategory.productCategory.productCategoryId.goe(from);
        }

        return QProductCategory.productCategory.productCategoryId.between(from, to);
    }

    private BooleanExpression nameContains(String name) {
        return name.isBlank() ? null : QProductCategory.productCategory.name.containsIgnoreCase(name);
    }

}
