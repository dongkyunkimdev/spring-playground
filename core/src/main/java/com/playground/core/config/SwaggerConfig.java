package com.playground.core.config;

import com.playground.core.annotation.ApiErrorCodeExample;
import com.playground.core.annotation.ApiExceptionExample;
import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.BusinessException;
import com.playground.core.exception.dto.ErrorReason;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.error.BaseErrorCode;
import com.playground.core.swagger.ExampleHolder;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Value("${playground.title}")
    private String title;

    @Value("${playground.version}")
    private String version;

    private final ApplicationContext applicationContext;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(info());
    }

    private Info info() {
        License license = new License();
        license.setUrl("https://github.com/dongkyunkimdev/spring-playground");
        license.setName("Github");

        return new Info()
            .title(this.title)
            .version(this.version)
            .license(license);
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            ApiExceptionExample apiExceptionExample = handlerMethod.getMethodAnnotation(ApiExceptionExample.class);
            ApiErrorCodeExample apiErrorCodeExample = handlerMethod.getMethodAnnotation(ApiErrorCodeExample.class);

            List<String> tags = getTags(handlerMethod);

            if (CollectionUtils.isNotEmpty(tags)) {
                operation.setTags(List.of(tags.get(0)));
            }

            if (apiExceptionExample != null) {
                generateExceptionResponseExample(operation, apiExceptionExample.value());
            }

            if (apiErrorCodeExample != null) {
                generateErrorCodeResponseExample(operation, apiErrorCodeExample.value());
            }

            return operation;
        };
    }

    private static List<String> getTags(HandlerMethod handlerMethod) {
        List<String> tags = new ArrayList<>();

        Tag[] methodTags = handlerMethod.getMethod().getAnnotationsByType(Tag.class);
        List<String> methodTagStrings = Arrays.stream(methodTags).map(Tag::name).toList();

        Tag[] classTags = handlerMethod.getClass().getAnnotationsByType(Tag.class);
        List<String> classTagStrings = Arrays.stream(classTags).map(Tag::name).toList();

        tags.addAll(methodTagStrings);
        tags.addAll(classTagStrings);

        return tags;
    }

    private void generateExceptionResponseExample(Operation operation, Class<?> type) {
        ApiResponses responses = operation.getResponses();
        Object bean = applicationContext.getBean(type);
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
            Arrays.stream(declaredFields)
                .filter(field -> field.getAnnotation(ExplainError.class) != null)
                .filter(field -> field.getType() == BusinessException.class)
                .map(
                    field -> {
                        try {
                            BusinessException exception = (BusinessException) field.get(bean);
                            ExplainError annotation = field.getAnnotation(ExplainError.class);
                            String value = annotation.value();
                            ErrorReason errorReason = exception.getErrorReason();

                            return ExampleHolder.builder()
                                .example(getSwaggerExample(value, errorReason))
                                .code(errorReason.getStatus())
                                .name(field.getName())
                                .build();
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    })
                .collect(groupingBy(ExampleHolder::getCode));

        addExamplesToResponses(responses, statusWithExampleHolders);
    }

    private Example getSwaggerExample(String value, ErrorReason errorReason) {
        ErrorResponse errorResponse = new ErrorResponse(errorReason, "요청 url.");

        Example example = new Example();
        example.description(value);
        example.setValue(errorResponse);

        return example;
    }

    private void addExamplesToResponses(ApiResponses responses, Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
        statusWithExampleHolders.forEach(
            (status, v) -> {
                Content content = new Content();
                MediaType mediaType = new MediaType();
                ApiResponse apiResponse = new ApiResponse();

                v.forEach(
                    exampleHolder -> {
                        mediaType.addExamples(
                            exampleHolder.getName(), exampleHolder.getExample());
                    });
                content.addMediaType("application/json", mediaType);
                apiResponse.setContent(content);
                responses.addApiResponse(status.toString(), apiResponse);
            });
    }

    private void generateErrorCodeResponseExample(Operation operation, Class<? extends BaseErrorCode> type) {
        ApiResponses responses = operation.getResponses();
        BaseErrorCode[] errorCodes = type.getEnumConstants();

        Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
            Arrays.stream(errorCodes)
                .map(
                    baseErrorCode -> {
                        try {
                            ErrorReason errorReason = baseErrorCode.getErrorReason();
                            return ExampleHolder.builder()
                                .example(
                                    getSwaggerExample(
                                        baseErrorCode.getExplainError(),
                                        errorReason))
                                .code(errorReason.getStatus())
                                .name(errorReason.getCode())
                                .build();
                        } catch (NoSuchFieldException e) {
                            throw new RuntimeException(e);
                        }
                    })
                .collect(groupingBy(ExampleHolder::getCode));

        addExamplesToResponses(responses, statusWithExampleHolders);
    }

}
