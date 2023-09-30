package com.playground.userservice.util.mapper.mapstruct;

import com.playground.userservice.application.port.in.usecase.dto.SignupCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignupInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.infrastructure.in.rest.dto.SignupRequest;
import com.playground.userservice.infrastructure.in.rest.dto.SignupResponse;
import com.playground.userservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface SignupMapStruct {

    SignupCommand requestToCommand(SignupRequest request);

    SignupInfo entityToInfo(User user);

    SignupResponse infoToResponse(SignupInfo info);

}
