package com.playground.userservice.infrastructure.in.rest;

import com.playground.core.annotation.ApiExceptionExample;
import com.playground.core.annotation.RestAdapter;
import com.playground.core.paging.SliceResponse;
import com.playground.userservice.application.port.in.usecase.GetUserListUseCase;
import com.playground.userservice.application.port.in.usecase.dto.GetUserListInfo;
import com.playground.userservice.infrastructure.in.rest.dto.GetUserListRequest;
import com.playground.userservice.infrastructure.in.rest.dto.GetUserListResponse;
import com.playground.userservice.util.mapper.GetUserListMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestAdapter
@RequiredArgsConstructor
public class GetUserListRestAdapter {

    private final GetUserListUseCase useCase;

    private final GetUserListMapper mapper;

    @Operation(summary = "유저 리스트 조회.")
    @Tag(name = "1-3. [유저 리스트 조회]")
    @GetMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public SliceResponse<GetUserListResponse> getUserList(
        @ParameterObject GetUserListRequest request,
        @ParameterObject @PageableDefault(size = 10, sort = "userId", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Slice<GetUserListInfo> infoSlice = useCase.execute(mapper.requestToCommand(request), pageable);

        return SliceResponse.of(infoSlice.map(mapper::infoToResponse));
    }

}
