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
    void 상품_리스트_조회_성공_fromProductId_필터링() throws Exception {
        // given
        final Long fromProductId = 5L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("fromProductId", String.valueOf(fromProductId));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.productId()).isGreaterThanOrEqualTo(fromProductId));
    }

    @Test
    void 상품_리스트_조회_성공_toProductId_필터링() throws Exception {
        // given
        final Long toProductId = 15L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("toProductId", String.valueOf(toProductId));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.productId()).isLessThanOrEqualTo(toProductId));
    }

    @Test
    void 상품_리스트_조회_성공_productIdInRange_필터링() throws Exception {
        // given
        final Long fromProductId = 5L;
        final Long toProductId = 20L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("fromProductId", String.valueOf(fromProductId))
            .param("toProductId", String.valueOf(toProductId));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.productId()).isBetween(fromProductId, toProductId));
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
    void 상품_리스트_조회_성공_fromProductCategoryId_필터링() throws Exception {
        // given
        final Long fromProductCategoryId = 20L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("fromProductCategoryId", String.valueOf(fromProductCategoryId));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.productCategory().productCategoryId()).isGreaterThanOrEqualTo(fromProductCategoryId));
    }

    @Test
    void 상품_리스트_조회_성공_toProductCategoryId_필터링() throws Exception {
        // given
        final Long toProductCategoryId = 10L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("toProductCategoryId", String.valueOf(toProductCategoryId));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.productCategory().productCategoryId()).isLessThanOrEqualTo(toProductCategoryId));
    }

    @Test
    void 상품_리스트_조회_성공_productCategoryIdInRange_필터링() throws Exception {
        // given
        final Long fromProductCategoryId = 10L;
        final Long toProductCategoryId = 20L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("fromProductCategoryId", String.valueOf(fromProductCategoryId))
            .param("toProductCategoryId", String.valueOf(toProductCategoryId));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetProductListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetProductListResponse> productResponseList = sliceResponse.getContent();
        productResponseList.forEach(product -> assertThat(product.productCategory().productCategoryId()).isBetween(fromProductCategoryId, toProductCategoryId));
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
        final Long fromProductId = 5L;
        final Long toProductId = 10L;
        final String productName = "apt";
        final BigDecimal minPrice = BigDecimal.valueOf(500);
        final BigDecimal maxPrice = BigDecimal.valueOf(1000);
        final Long fromProductCategoryId = 1L;
        final Long toProductCategoryId = 5L;
        final String productCategoryName = "tron";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/products")
            .param("fromProductId", String.valueOf(fromProductId))
            .param("toProductId", String.valueOf(toProductId))
            .param("productName", productName)
            .param("minPrice", String.valueOf(minPrice))
            .param("maxPrice", String.valueOf(maxPrice))
            .param("fromProductCategoryId", String.valueOf(fromProductCategoryId))
            .param("toProductCategoryId", String.valueOf(toProductCategoryId))
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
            assertThat(product.productId()).isBetween(fromProductId, toProductId);
            assertThat(product.name()).containsIgnoringCase(productName);
            assertThat(product.price()).isBetween(minPrice, maxPrice);
            assertThat(product.productCategory().productCategoryId()).isBetween(fromProductCategoryId, toProductCategoryId);
            assertThat(product.productCategory().name()).containsIgnoringCase(productCategoryName);
        });
    }

}
