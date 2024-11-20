package com.adepuu.fezz4ubackend.infrastructure.auth.controller;

import com.adepuu.fezz4ubackend.common.response.Response;
import com.adepuu.fezz4ubackend.infrastructure.auth.Claims;
import com.adepuu.fezz4ubackend.infrastructure.auth.dto.LoginRequestDTO;
import com.adepuu.fezz4ubackend.usecase.auth.LoginUsecase;
import com.adepuu.fezz4ubackend.usecase.auth.TokenBlacklistUsecase;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final LoginUsecase loginUsecase;

  private final TokenBlacklistUsecase TokenBlacklistUsecase;

  public AuthController(LoginUsecase loginUsecase,
      com.adepuu.fezz4ubackend.usecase.auth.TokenBlacklistUsecase tokenBlacklistUsecase) {
    this.loginUsecase = loginUsecase;

    TokenBlacklistUsecase = tokenBlacklistUsecase;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Validated @RequestBody LoginRequestDTO req) {
    return Response.successfulResponse("Login successful", loginUsecase.authenticateUser(req));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout() {
    String token = Claims.getJwtTokeString();
    String expiredAt = Claims.getJwtExpirationDate();
    TokenBlacklistUsecase.blacklistToken(token, expiredAt); // Adjust duration as needed
    return Response.successfulResponse("Logout successful", null);
  }
}
