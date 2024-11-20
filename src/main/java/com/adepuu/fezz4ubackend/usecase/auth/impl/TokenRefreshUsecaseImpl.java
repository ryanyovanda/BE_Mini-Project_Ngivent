package com.adepuu.fezz4ubackend.usecase.auth.impl;

import com.adepuu.fezz4ubackend.infrastructure.auth.dto.TokenPairResponseDTO;
import com.adepuu.fezz4ubackend.usecase.auth.TokenGenerationUsecase;
import com.adepuu.fezz4ubackend.usecase.auth.TokenRefreshUsecase;
import org.springframework.stereotype.Service;

@Service
public class TokenRefreshUsecaseImpl implements TokenRefreshUsecase {
    private final TokenGenerationUsecase tokenService;

    public TokenRefreshUsecaseImpl(TokenGenerationUsecase tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public TokenPairResponseDTO refreshAccessToken(String refreshToken) {
        String newAccessToken = tokenService.refreshAccessToken(refreshToken);
        return new TokenPairResponseDTO(newAccessToken, refreshToken, "Bearer");
    }
}
