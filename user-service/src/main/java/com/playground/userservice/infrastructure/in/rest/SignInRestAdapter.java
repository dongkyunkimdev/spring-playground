package com.playground.userservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiExceptionExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.userservice.application.port.in.usecase.SignInUseCase;
import com.playground.userservice.infrastructure.in.rest.docs.SignInExceptionDocs;
import com.playground.userservice.infrastructure.in.rest.dto.SignInRequest;
import com.playground.userservice.infrastructure.in.rest.dto.SignInResponse;
import com.playground.userservice.util.mapper.SignInMapper;
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
public class SignInRestAdapter {

    private final SignInUseCase useCase;

    private final SignInMapper mapper;

    @Operation(summary = "로그인.")
    @Tag(name = "1-2. [로그인]")
    @ApiExceptionExample(SignInExceptionDocs.class)
    @PostMapping("/v1/users/signin")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signIn(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "로그인 정보.") @RequestBody(required = true) @Valid final SignInRequest request
    ) {
        return mapper.infoToResponse(useCase.execute(mapper.requestToCommand(request)));
    }

}
