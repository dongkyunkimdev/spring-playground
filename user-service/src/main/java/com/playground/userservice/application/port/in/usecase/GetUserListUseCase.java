package com.playground.userservice.application.port.in.usecase;

import com.playground.userservice.application.port.in.usecase.dto.GetUserListCommand;
import com.playground.userservice.application.port.in.usecase.dto.GetUserListInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GetUserListUseCase {

    Slice<GetUserListInfo> execute(GetUserListCommand command, Pageable pageable);

}
