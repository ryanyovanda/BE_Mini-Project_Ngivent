package com.ryanyovanda.backendminipro.infrastructure.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ryanyovanda.backendminipro.common.response.Response;
import com.ryanyovanda.backendminipro.infrastructure.auth.Claims;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.CreateUserRequestDTO;
import com.ryanyovanda.backendminipro.usecase.user.CreateUserUsecase;
import com.ryanyovanda.backendminipro.usecase.user.GetUsersUseCase;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/api/v1/users")
public class UsersPublicController {
  private final GetUsersUseCase getUsersUseCase;
  private final CreateUserUsecase createUserUsecase;

  public UsersPublicController(final GetUsersUseCase getUsersUseCase, CreateUserUsecase createUserUsecase) {
    this.getUsersUseCase = getUsersUseCase;
    this.createUserUsecase = createUserUsecase;
  }

  @PostMapping("/register")
  public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDTO req) {
    var newUser = createUserUsecase.createUser(req);
    return Response.successfulResponse("Create new user success", newUser);
  }
}
