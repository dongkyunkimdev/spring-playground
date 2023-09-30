package com.playground.userservice.util.mapper.mapstruct;

import com.playground.userservice.application.port.in.usecase.dto.SignInCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignInInfo;
import com.playground.userservice.infrastructure.in.rest.dto.SignInRequest;
import com.playground.userservice.infrastructure.in.rest.dto.SignInResponse;
import com.playground.userservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface SignInMapStruct {

    SignInCommand requestToCommand(SignInRequest request);

    SignInResponse infoToResponse(SignInInfo info);

}
