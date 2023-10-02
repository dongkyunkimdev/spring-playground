package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.core.paging.SliceResponse;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryListResponse;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.playground.productservice.support.AssertUtil.assertSuccessResponse;
import static com.playground.productservice.support.ControllerTestUtil.createRequestBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class GetProductCategoryListRestAdapterTest extends ControllerTest {

    @Test
    void 상품_카테고리_리스트_조회_성공() throws Exception {
        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products/categories");

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductCategoryListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(sliceResponse.getContent()).hasSize(10);
        assertThat(sliceResponse.getPage()).isZero();
        assertThat(sliceResponse.getSize()).isEqualTo(10);
        assertThat(sliceResponse.isHasPrevious()).isFalse();
        assertThat(sliceResponse.isHasNext()).isTrue();

        List<GetProductCategoryListResponse> productCategoryResponseList = sliceResponse.getContent();
        productCategoryResponseList.forEach(productCategory -> {
            assertThat(productCategory.productCategoryId()).isNotNull();
            assertThat(productCategory.name()).isNotNull();
            assertThat(productCategory.createdAt()).isNotNull();
            assertThat(productCategory.updatedAt()).isNotNull();
        });
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_productCategoryName_필터링() throws Exception {
        // given
        final String productCategoryName = "co";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products/categories")
            .param("productCategoryName", productCategoryName);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductCategoryListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductCategoryListResponse> productCategoryResponseList = sliceResponse.getContent();
        productCategoryResponseList.forEach(productCategory -> assertThat(productCategory.name()).containsIgnoringCase(productCategoryName));
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_name_asc_정렬() throws Exception {
        // given
        final String order = "name,ASC";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products/categories")
            .param("sort", order);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductCategoryListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductCategoryListResponse> productCategoryResponseList = sliceResponse.getContent();
        assertThat(productCategoryResponseList.get(0).name()).startsWithIgnoringCase("A");
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_productCategoryName_필터링_name_asc_정렬() throws Exception {
        // given
        final String productCategoryName = "c";
        final String order = "name,ASC";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products/categories")
            .param("productCategoryName", productCategoryName)
            .param("sort", order);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductCategoryListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductCategoryListResponse> productCategoryResponseList = sliceResponse.getContent();
        productCategoryResponseList.forEach(productCategory -> assertThat(productCategory.name()).containsIgnoringCase(productCategoryName));
        assertThat(productCategoryResponseList.get(0).name()).startsWithIgnoringCase("A");
    }

}
