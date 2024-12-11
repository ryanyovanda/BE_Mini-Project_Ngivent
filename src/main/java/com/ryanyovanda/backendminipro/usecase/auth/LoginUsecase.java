package com.adepuu.fezz4ubackend.usecase.auth;

import com.adepuu.fezz4ubackend.infrastructure.auth.dto.LoginRequestDTO;
import com.adepuu.fezz4ubackend.infrastructure.auth.dto.TokenPairResponseDTO;

public interface LoginUsecase {
  TokenPairResponseDTO authenticateUser(LoginRequestDTO req);
}