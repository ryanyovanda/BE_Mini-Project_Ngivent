package com.ryanyovanda.backendminipro.usecase.auth;

import com.ryanyovanda.backendminipro.infrastructure.auth.dto.LoginRequestDTO;
import com.ryanyovanda.backendminipro.infrastructure.auth.dto.TokenPairResponseDTO;

public interface LoginUsecase {
  TokenPairResponseDTO authenticateUser(LoginRequestDTO req);
}