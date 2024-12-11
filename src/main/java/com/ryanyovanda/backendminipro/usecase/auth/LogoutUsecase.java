package com.adepuu.fezz4ubackend.usecase.auth;

import com.adepuu.fezz4ubackend.infrastructure.auth.dto.LogoutRequestDTO;

public interface LogoutUsecase {
    Boolean logoutUser(LogoutRequestDTO req);
}
