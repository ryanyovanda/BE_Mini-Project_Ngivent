package com.adepuu.fezz4ubackend.usecase.auth;

import com.adepuu.fezz4ubackend.infrastructure.auth.dto.LoginRequestDTO;
import com.adepuu.fezz4ubackend.infrastructure.auth.dto.LoginResponseDTO;

public interface LoginUsecase {
  LoginResponseDTO authenticateUser(LoginRequestDTO req);
}