package com.playground.userservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiExceptionExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.userservice.application.port.in.usecase.SignUpUseCase;
import com.playground.userservice.infrastructure.in.rest.docs.SignUpExceptionDocs;
import com.playground.userservice.infrastructure.in.rest.dto.SignUpRequest;
import com.playground.userservice.infrastructure.in.rest.dto.SignUpResponse;
import com.playground.userservice.util.mapper.SignUpMapper;
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
public class SignUpRestAdapter {

    private final SignUpUseCase signUpUseCase;

    private final SignUpMapper mapper;

    @Operation(summary = "회원가입.")
    @Tag(name = "1-1. [회원가입]")
    @ApiExceptionExample(SignUpExceptionDocs.class)
    @PostMapping("/v1/users/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponse signUp(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원가입 정보.") @RequestBody(required = true) @Valid final SignUpRequest request
    ) {
        return mapper.infoToResponse(signUpUseCase.execute(mapper.requestToCommand(request)));
    }

}
