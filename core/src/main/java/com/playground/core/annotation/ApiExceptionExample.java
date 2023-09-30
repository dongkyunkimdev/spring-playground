package com.playground.core.annotation;

import com.playground.core.swagger.SwaggerExampleExceptions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiExceptionExample {

    Class<? extends SwaggerExampleExceptions> value();

}
