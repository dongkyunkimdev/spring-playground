package com.playground.userservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiErrorExceptionsExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.userservice.application.port.in.usecase.SignupUseCase;
import com.playground.userservice.infrastructure.in.rest.docs.SignupExceptionDocs;
import com.playground.userservice.infrastructure.in.rest.dto.SignupRequest;
import com.playground.userservice.infrastructure.in.rest.dto.SignupResponse;
import com.playground.userservice.util.mapper.SignupMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class SignupRestAdapter {

    private final SignupUseCase signupUseCase;

    private final SignupMapper mapper;

    @Operation(summary = "회원가입.")
    @Tag(name = "1-1. [회원가입]")
    @ApiErrorExceptionsExample(SignupExceptionDocs.class)
    @PostMapping("/v1/users/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public SignupResponse signup(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원가입 정보.") @RequestBody(required = true) @Valid final SignupRequest request
    ) {
        return mapper.infoToResponse(signupUseCase.execute(mapper.requestToCommand(request)));
    }

}
