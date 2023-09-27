package com.playground.productservice.infrastructure.dao;

import com.playground.core.paging.QueryDslUtil;
import com.playground.core.paging.SliceUtil;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.QProductCategory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCategoryRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public Slice<ProductCategory> findAllByIdRangeAndName(Long fromProductCategoryId, Long toProductCategoryId, String productCategoryName, Pageable pageable) {
        List<ProductCategory> content = queryFactory.selectFrom(QProductCategory.productCategory)
            .where(
                productCategoryIdInRange(fromProductCategoryId, toProductCategoryId),
                nameContains(productCategoryName)
            )
            .orderBy(QueryDslUtil.createOrderSpecifiers(ProductCategory.class, pageable))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        return SliceUtil.createSlice(content, pageable);
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
        return StringUtils.isBlank(name) ? null : QProductCategory.productCategory.name.containsIgnoreCase(name);
    }

}
