package com.playground.productservice.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.dto.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected SuccessResponse getSuccessResponse(ResultActions result) throws UnsupportedEncodingException, JsonProcessingException {
        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        return objectMapper.readValue(responseMessage, new TypeReference<>() {
        });
    }

    protected ErrorResponse getErrorResponse(ResultActions result) throws UnsupportedEncodingException, JsonProcessingException {
        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        return objectMapper.readValue(responseMessage, new TypeReference<>() {
        });
    }

}
