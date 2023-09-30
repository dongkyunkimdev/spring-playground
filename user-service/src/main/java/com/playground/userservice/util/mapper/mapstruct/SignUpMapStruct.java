package com.playground.userservice.util.mapper.mapstruct;

import com.playground.userservice.application.port.in.usecase.dto.SignUpCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignUpInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.infrastructure.in.rest.dto.SignUpRequest;
import com.playground.userservice.infrastructure.in.rest.dto.SignUpResponse;
import com.playground.userservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface SignUpMapStruct {

    SignUpCommand requestToCommand(SignUpRequest request);

    SignUpInfo entityToInfo(User user);

    SignUpResponse infoToResponse(SignUpInfo info);

}
