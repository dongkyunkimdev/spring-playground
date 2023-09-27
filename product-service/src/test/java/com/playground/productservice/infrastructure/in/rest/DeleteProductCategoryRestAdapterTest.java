package com.playground.productservice.infrastructure.in.rest;

import com.playground.core.exception.dto.ErrorResponse;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.exception.ProductErrorCode;
import com.playground.productservice.infrastructure.dao.ProductCategoryRepository;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.playground.productservice.support.AssertUtil.assertErrorResponse;
import static com.playground.productservice.support.ControllerTestUtil.createRequestBuilder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class DeleteProductCategoryRestAdapterTest extends ControllerTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void 상품_카테고리_삭제_성공() throws Exception {
        // given
        ProductCategory savedProductCategory = productCategoryRepository.save(ProductCategory.builder().name("test category for delete").build());

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.DELETE, "/v1/product/category/{productCategoryId}", savedProductCategory.getProductCategoryId());

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isNoContent());
    }

    @Test
    void 상품_카테고리_삭제_실패_상품_카테고리가_존재하지_않음() throws Exception {
        // given
        final Long productCategoryId = 98123L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.DELETE, "/v1/product/category/{productCategoryId}", productCategoryId);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isNotFound());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, ProductErrorCode.PRODUCT_CATEGORY_NOT_FOUND);
    }

    @Test
    void 상품_카테고리_삭제_실패_상품_카테고리가_참조되고_있음() throws Exception {
        // given
        final Long productCategoryId = 1L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.DELETE, "/v1/product/category/{productCategoryId}", productCategoryId);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isConflict());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, ProductErrorCode.PRODUCT_CATEGORY_REFERENCED);
    }

}
