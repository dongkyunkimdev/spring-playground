package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.core.paging.SliceResponse;
import com.playground.productservice.infrastructure.in.rest.dto.CommonProductCategoryResponse;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductListResponse;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.playground.productservice.support.AssertUtil.assertSuccessResponse;
import static com.playground.productservice.support.ControllerTestUtil.createRequestBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class GetProductListRestAdapterTest extends ControllerTest {

    @Test
    void 상품_리스트_조회_성공() throws Exception {
        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products");

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(sliceResponse.getContent()).hasSize(10);
        assertThat(sliceResponse.getPage()).isZero();
        assertThat(sliceResponse.getSize()).isEqualTo(10);
        assertThat(sliceResponse.isHasPrevious()).isFalse();
        assertThat(sliceResponse.isHasNext()).isTrue();

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> {
            assertThat(product.productId()).isNotNull();
            assertThat(product.name()).isNotNull();
            assertThat(product.stock()).isNotNull();
            assertThat(product.price()).isNotNull();
            assertThat(product.createdAt()).isNotNull();
            assertThat(product.updatedAt()).isNotNull();

            CommonProductCategoryResponse productCategory = product.productCategory();
            assertThat(productCategory.productCategoryId()).isNotNull();
            assertThat(productCategory.name()).isNotNull();
            assertThat(productCategory.createdAt()).isNotNull();
            assertThat(productCategory.updatedAt()).isNotNull();
        });
    }

    @Test
    void 상품_리스트_조회_성공_productName_필터링() throws Exception {
        // given
        final String productName = "b";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("productName", productName);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.name()).containsIgnoringCase(productName));
    }

    @Test
    void 상품_리스트_조회_성공_minPrice_필터링() throws Exception {
        // given
        final BigDecimal minPrice = BigDecimal.valueOf(30);

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("minPrice", String.valueOf(minPrice));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.price()).isGreaterThanOrEqualTo(minPrice));
    }

    @Test
    void 상품_리스트_조회_성공_maxPrice_필터링() throws Exception {
        // given
        final BigDecimal maxPrice = BigDecimal.valueOf(50);

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("maxPrice", String.valueOf(maxPrice));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.price()).isLessThanOrEqualTo(maxPrice));
    }

    @Test
    void 상품_리스트_조회_성공_priceInRange_필터링() throws Exception {
        // given
        final BigDecimal minPrice = BigDecimal.valueOf(30);
        final BigDecimal maxPrice = BigDecimal.valueOf(500);

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("minPrice", String.valueOf(minPrice))
            .param("maxPrice", String.valueOf(maxPrice));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.price()).isBetween(minPrice, maxPrice));
    }

    @Test
    void 상품_리스트_조회_성공_productCategoryName_필터링() throws Exception {
        // given
        final String productCategoryName = "ac";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("productCategoryName", productCategoryName);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.productCategory().name()).containsIgnoringCase(productCategoryName));
    }

    @Test
    void 상품_리스트_조회_성공_all_필터링() throws Exception {
        // given
        final String productName = "apt";
        final BigDecimal minPrice = BigDecimal.valueOf(500);
        final BigDecimal maxPrice = BigDecimal.valueOf(1000);
        final String productCategoryName = "tron";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("productName", productName)
            .param("minPrice", String.valueOf(minPrice))
            .param("maxPrice", String.valueOf(maxPrice))
            .param("productCategoryName", productCategoryName);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> {
            assertThat(product.name()).containsIgnoringCase(productName);
            assertThat(product.price()).isBetween(minPrice, maxPrice);
            assertThat(product.productCategory().name()).containsIgnoringCase(productCategoryName);
        });
    }

}
