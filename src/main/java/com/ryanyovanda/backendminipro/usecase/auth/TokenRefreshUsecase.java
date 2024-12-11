package com.ryanyovanda.backendminipro.usecase.auth;

import com.ryanyovanda.backendminipro.infrastructure.auth.dto.TokenPairResponseDTO;

public interface TokenRefreshUsecase {
    TokenPairResponseDTO refreshAccessToken(String refreshToken);
}
