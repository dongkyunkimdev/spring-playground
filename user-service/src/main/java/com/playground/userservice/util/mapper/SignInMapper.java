package com.playground.userservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.userservice.application.port.in.usecase.dto.SignInCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignInInfo;
import com.playground.userservice.infrastructure.in.rest.dto.SignInRequest;
import com.playground.userservice.infrastructure.in.rest.dto.SignInResponse;
import com.playground.userservice.util.mapper.mapstruct.SignInMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class SignInMapper {

    private final SignInMapStruct mapStruct;

    public SignInCommand requestToCommand(SignInRequest request) {
        return mapStruct.requestToCommand(request);
    }

    public SignInResponse infoToResponse(SignInInfo info) {
        return mapStruct.infoToResponse(info);
    }

}
