package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.productservice.domain.exception.ProductErrorCode;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductCategoryResponse;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.playground.productservice.support.ControllerTestUtil.createRequestBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class UpdateProductCategoryRestAdapterTest extends ControllerTest {

    @Test
    void 상품_카테고리_수정_성공() throws Exception {
        // given
        final Long productCategoryId = 1L;
        final UpdateProductCategoryRequest requestDto = new UpdateProductCategoryRequest("Test Toys");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.PATCH, "/v1/product/category/{productCategoryId}", productCategoryId)
                .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(HttpStatus.OK.value());

        UpdateProductCategoryResponse productCategoryResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productCategoryResponse.productCategoryId()).isEqualTo(productCategoryId);
        assertThat(productCategoryResponse.name()).isEqualTo(requestDto.name());
    }

    @Test
    void 상품_카테고리_수정_실패_상품_카테고리가_존재하지_않음() throws Exception {
        // given
        final Long productCategoryId = 81273L;
        final UpdateProductCategoryRequest requestDto = new UpdateProductCategoryRequest("Test Toys");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.PATCH, "/v1/product/category/{productCategoryId}", productCategoryId)
                .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isNotFound());

        ErrorResponse responseDto = getErrorResponse(result);
        ProductErrorCode errorCode = ProductErrorCode.PRODUCT_CATEGORY_NOT_FOUND;

        assertThat(responseDto.isSuccess()).isFalse();
        assertThat(responseDto.getStatus()).isEqualTo(errorCode.getStatus());
        assertThat(responseDto.getCode()).isEqualTo(errorCode.getCode());
        assertThat(responseDto.getReason()).isEqualTo(errorCode.getReason());
    }

    @Test
    void 상품_카테고리_수정_실패_이름이_중복됨() throws Exception {
        // given
        final Long productCategoryId = 1L;
        final UpdateProductCategoryRequest requestDto = new UpdateProductCategoryRequest("Electronics");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.PATCH, "/v1/product/category/{productCategoryId}", productCategoryId)
                .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isConflict());

        ErrorResponse responseDto = getErrorResponse(result);
        ProductErrorCode errorCode = ProductErrorCode.PRODUCT_CATEGORY_NAME_DUPLICATED;

        assertThat(responseDto.isSuccess()).isFalse();
        assertThat(responseDto.getStatus()).isEqualTo(errorCode.getStatus());
        assertThat(responseDto.getCode()).isEqualTo(errorCode.getCode());
        assertThat(responseDto.getReason()).isEqualTo(errorCode.getReason());
    }

}
