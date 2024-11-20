package com.adepuu.fezz4ubackend.usecase.auth.impl;

import com.adepuu.fezz4ubackend.common.exceptions.DataNotFoundException;
import com.adepuu.fezz4ubackend.infrastructure.auth.dto.LoginRequestDTO;
import com.adepuu.fezz4ubackend.infrastructure.auth.dto.LoginResponseDTO;
import com.adepuu.fezz4ubackend.usecase.auth.LoginUsecase;
import com.adepuu.fezz4ubackend.usecase.auth.TokenGenerationUsecase;

import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Log
@Service
public class LoginUsecaseImpl implements LoginUsecase {
  private final AuthenticationManager authenticationManager;
  private final TokenGenerationUsecase tokenService;

  public LoginUsecaseImpl(AuthenticationManager authenticationManager, TokenGenerationUsecase tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @Override
  public LoginResponseDTO authenticateUser(LoginRequestDTO req) {
    try {
      log.info("Loggingin with");
      log.info(req.getEmail());
      log.info(req.getPassword());
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
      );
      String token = tokenService.generateToken(authentication);
      return new LoginResponseDTO(token);
    } catch (AuthenticationException e) {
      throw new DataNotFoundException("Wrong credentials");
    }
  }
}