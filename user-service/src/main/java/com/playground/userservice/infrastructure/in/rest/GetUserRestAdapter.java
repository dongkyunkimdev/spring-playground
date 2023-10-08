package com.playground.userservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiExceptionExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.userservice.application.port.in.usecase.GetUserUseCase;
import com.playground.userservice.application.port.in.usecase.dto.GetUserCommand;
import com.playground.userservice.infrastructure.in.rest.docs.GetUserExceptionDocs;
import com.playground.userservice.infrastructure.in.rest.dto.GetUserResponse;
import com.playground.userservice.util.mapper.GetUserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class GetUserRestAdapter {

    private final GetUserUseCase useCase;

    private final GetUserMapper mapper;

    @Operation(summary = "유저 조회.")
    @Tag(name = "1-4. [유저 조회]")
    @ApiExceptionExample(GetUserExceptionDocs.class)
    @GetMapping(value = "/v1/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GetUserResponse getUser(
        @Parameter(description = "유저 ID.", example = "1") @PathVariable("userId") final Long userId
    ) {
        return mapper.infoToResponse(useCase.execute(new GetUserCommand(userId)));
    }

}
