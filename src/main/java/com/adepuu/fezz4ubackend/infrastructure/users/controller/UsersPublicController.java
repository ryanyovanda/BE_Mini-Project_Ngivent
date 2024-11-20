package com.adepuu.fezz4ubackend.infrastructure.users.controller;

import com.adepuu.fezz4ubackend.common.response.Response;
import com.adepuu.fezz4ubackend.infrastructure.auth.Claims;
import com.adepuu.fezz4ubackend.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.adepuu.fezz4ubackend.infrastructure.users.dto.CreateUserRequestDTO;
import com.adepuu.fezz4ubackend.usecase.user.CreateUserUsecase;
import com.adepuu.fezz4ubackend.usecase.user.GetUsersUseCase;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

  //  Simple RBAC where only logged in admins are allowed to access get all users endpoint
  @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
  @GetMapping
  public ResponseEntity<?> getUsers(@RequestParam final String isActive) {
    String email = Claims.getEmailFromJwt();
    log.info("Requester email is: " + email);
    return Response.successfulResponse("Get all users success", getUsersUseCase.getAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable final Long id) {
    return Response.successfulResponse("Get user details success", getUsersUseCase.getUserById(id));
  }

  @PostMapping("/register")
  public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDTO req) {
    var result = createUserUsecase.createUser(req);
    return Response.successfulResponse("Create new user success", result);
  }

  @PostMapping("/bulk")
  public ResponseEntity<?> createUserBulk(@RequestBody BulkCreateUserRequestDTO req) {
    return Response.successfulResponse("Create new user success", createUserUsecase.bulkCreateUser(req));
  }
}
