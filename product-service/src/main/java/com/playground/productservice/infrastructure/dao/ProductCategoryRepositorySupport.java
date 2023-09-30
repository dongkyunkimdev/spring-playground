package com.playground.productservice.infrastructure.dao;

import com.playground.core.paging.QueryDslUtil;
import com.playground.core.paging.SliceUtil;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.QProductCategory;
import com.playground.productservice.infrastructure.dao.dto.GetProductCategoryListSearchCondition;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.playground.productservice.infrastructure.dao.ConditionBuilder.productCategoryIdInRange;
import static com.playground.productservice.infrastructure.dao.ConditionBuilder.productCategoryNameContains;

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

}
