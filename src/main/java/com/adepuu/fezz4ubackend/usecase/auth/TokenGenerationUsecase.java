package com.adepuu.fezz4ubackend.usecase.auth;

import org.springframework.security.core.Authentication;

public interface TokenGenerationUsecase {
  String generateToken(Authentication authentication);
}
