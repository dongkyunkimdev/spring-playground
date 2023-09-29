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
        });
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_fromProductCategoryId_필터링() throws Exception {
        // given
        final Long fromProductCategoryId = 5L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products/categories")
            .param("fromProductCategoryId", String.valueOf(fromProductCategoryId));

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
        productCategoryResponseList.forEach(productCategory -> assertThat(productCategory.productCategoryId()).isGreaterThanOrEqualTo(fromProductCategoryId));
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_toProductCategoryId_필터링() throws Exception {
        // given
        final Long toProductCategoryId = 11L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products/categories")
            .param("toProductCategoryId", String.valueOf(toProductCategoryId));

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
        productCategoryResponseList.forEach(productCategory -> assertThat(productCategory.productCategoryId()).isLessThanOrEqualTo(toProductCategoryId));
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_productCategoryIdInRange_필터링() throws Exception {
        // given
        final Long fromProductCategoryId = 5L;
        final Long toProductCategoryId = 11L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products/categories")
            .param("fromProductCategoryId", String.valueOf(fromProductCategoryId))
            .param("toProductCategoryId", String.valueOf(toProductCategoryId));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductCategoryListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(sliceResponse.getContent()).hasSize(7);
        assertThat(sliceResponse.getPage()).isZero();
        assertThat(sliceResponse.getSize()).isEqualTo(7);
        assertThat(sliceResponse.isHasPrevious()).isFalse();
        assertThat(sliceResponse.isHasNext()).isFalse();

        List<GetProductCategoryListResponse> productCategoryResponseList = sliceResponse.getContent();
        productCategoryResponseList.forEach(productCategory -> assertThat(productCategory.productCategoryId()).isBetween(fromProductCategoryId, toProductCategoryId));
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_productCategoryName_필터링() throws Exception {
        // given
        final String productCategoryName = "co";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products/categories")
            .param("productCategoryName", productCategoryName)
            .param("size", String.valueOf(1));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductCategoryListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(sliceResponse.getContent()).hasSize(1);
        assertThat(sliceResponse.getPage()).isZero();
        assertThat(sliceResponse.getSize()).isEqualTo(1);
        assertThat(sliceResponse.isHasPrevious()).isFalse();
        assertThat(sliceResponse.isHasNext()).isTrue();

        List<GetProductCategoryListResponse> productCategoryResponseList = sliceResponse.getContent();
        productCategoryResponseList.forEach(productCategory -> assertThat(productCategory.name()).containsIgnoringCase(productCategoryName));
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_fromId_toId_name_필터링() throws Exception {
        // given
        final Long fromProductCategoryId = 1L;
        final Long toProductCategoryId = 10L;
        final String productCategoryName = "es";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products/categories")
            .param("fromProductCategoryId", String.valueOf(fromProductCategoryId))
            .param("toProductCategoryId", String.valueOf(toProductCategoryId))
            .param("productCategoryName", productCategoryName)
            .param("size", String.valueOf(1));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductCategoryListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(sliceResponse.getContent()).isNotEmpty();
        assertThat(sliceResponse.getPage()).isZero();
        assertThat(sliceResponse.getSize()).isPositive();
        assertThat(sliceResponse.isHasPrevious()).isFalse();
        assertThat(sliceResponse.isHasNext()).isTrue();

        List<GetProductCategoryListResponse> productCategoryResponseList = sliceResponse.getContent();
        productCategoryResponseList.forEach(productCategory -> {
            assertThat(productCategory.productCategoryId()).isBetween(fromProductCategoryId, toProductCategoryId);
            assertThat(productCategory.name()).containsIgnoringCase(productCategoryName);
        });
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_name_asc_정렬() throws Exception {
        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products/categories")
            .param("sort", "name,ASC");

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductCategoryListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(sliceResponse.getContent()).isNotEmpty();
        assertThat(sliceResponse.getPage()).isZero();
        assertThat(sliceResponse.getSize()).isPositive();
        assertThat(sliceResponse.isHasPrevious()).isFalse();
        assertThat(sliceResponse.isHasNext()).isTrue();

        List<GetProductCategoryListResponse> productCategoryResponseList = sliceResponse.getContent();
        assertThat(productCategoryResponseList.get(0).name()).startsWith("A");
    }

}
