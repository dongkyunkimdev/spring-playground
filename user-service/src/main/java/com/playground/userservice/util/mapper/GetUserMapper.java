package com.playground.userservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.userservice.application.port.in.usecase.dto.GetUserInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.util.mapper.mapstruct.GetUserMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class GetUserMapper {

    private final GetUserMapStruct mapStruct;

    public GetUserInfo entityToInfo(User user) {
        return mapStruct.entityToInfo(user);
    }

}
