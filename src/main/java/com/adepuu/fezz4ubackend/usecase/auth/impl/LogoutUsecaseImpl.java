package com.adepuu.fezz4ubackend.usecase.auth.impl;

import com.adepuu.fezz4ubackend.infrastructure.auth.dto.LogoutRequestDTO;
import com.adepuu.fezz4ubackend.usecase.auth.LogoutUsecase;
import com.adepuu.fezz4ubackend.usecase.auth.TokenBlacklistUsecase;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LogoutUsecaseImpl implements LogoutUsecase {
    private final JwtDecoder jwtDecoder;
    private final TokenBlacklistUsecase TokenBlacklistUsecase;


    public LogoutUsecaseImpl(JwtDecoder jwtDecoder, com.adepuu.fezz4ubackend.usecase.auth.TokenBlacklistUsecase tokenBlacklistUsecase) {
        this.jwtDecoder = jwtDecoder;
        TokenBlacklistUsecase = tokenBlacklistUsecase;
    }

    @Override
    public Boolean logoutUser(LogoutRequestDTO req) {
        Jwt accessToken = jwtDecoder.decode(req.getAccessToken());
        Jwt refreshToken = jwtDecoder.decode(req.getRefreshToken());

        TokenBlacklistUsecase.blacklistToken(accessToken.getTokenValue(), Objects.requireNonNull(accessToken.getExpiresAt()).toString());
        TokenBlacklistUsecase.blacklistToken(refreshToken.getTokenValue(), Objects.requireNonNull(refreshToken.getExpiresAt()).toString());
        return Boolean.TRUE;
    }
}
