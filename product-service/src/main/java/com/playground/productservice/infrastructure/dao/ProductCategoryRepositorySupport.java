package com.playground.productservice.infrastructure.dao;

import com.playground.core.paging.QueryDslUtil;
import com.playground.core.paging.SliceUtil;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.QProductCategory;
import com.playground.productservice.infrastructure.dao.dto.GetProductCategoryListSearchCondition;
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

    public Slice<ProductCategory> findListBySearchCondition(GetProductCategoryListSearchCondition searchCondition, Pageable pageable) {
        List<ProductCategory> content = queryFactory.selectFrom(QProductCategory.productCategory)
            .where(
                productCategoryIdInRange(searchCondition.fromProductCategoryId(), searchCondition.toProductCategoryId()),
                productCategoryNameContains(searchCondition.productCategoryName())
            )
            .orderBy(QueryDslUtil.createOrderSpecifiers(ProductCategory.class, pageable))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        return SliceUtil.createSlice(content, pageable);
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
