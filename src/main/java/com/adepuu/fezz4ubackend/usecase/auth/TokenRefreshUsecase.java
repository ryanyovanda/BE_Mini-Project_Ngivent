package com.adepuu.fezz4ubackend.usecase.auth;

import com.adepuu.fezz4ubackend.infrastructure.auth.dto.TokenPairResponseDTO;

public interface TokenRefreshUsecase {
    TokenPairResponseDTO refreshAccessToken(String refreshToken);
}
