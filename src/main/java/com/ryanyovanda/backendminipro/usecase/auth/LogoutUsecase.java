package com.ryanyovanda.backendminipro.usecase.auth;

import com.ryanyovanda.backendminipro.infrastructure.auth.dto.LogoutRequestDTO;

public interface LogoutUsecase {
    Boolean logoutUser(LogoutRequestDTO req);
}
