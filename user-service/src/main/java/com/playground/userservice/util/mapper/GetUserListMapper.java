package com.playground.userservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.userservice.application.port.in.usecase.dto.GetUserListCommand;
import com.playground.userservice.application.port.in.usecase.dto.GetUserListInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.infrastructure.dao.dto.GetUserListSearchCondition;
import com.playground.userservice.infrastructure.in.rest.dto.GetUserListRequest;
import com.playground.userservice.infrastructure.in.rest.dto.GetUserListResponse;
import com.playground.userservice.util.mapper.mapstruct.GetUserListMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class GetUserListMapper {

    private final GetUserListMapStruct mapStruct;

    public GetUserListCommand requestToCommand(GetUserListRequest request) {
        return mapStruct.requestToCommand(request);
    }

    public GetUserListInfo entityToInfo(User user) {
        return mapStruct.entityToInfo(user);
    }

    public GetUserListResponse infoToResponse(GetUserListInfo info) {
        return mapStruct.infoToResponse(info);
    }

    public GetUserListSearchCondition commandToSearchCondition(GetUserListCommand command) {
        return mapStruct.commandToSearchCondition(command);
    }

}
