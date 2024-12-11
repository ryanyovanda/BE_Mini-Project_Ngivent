package com.ryanyovanda.backendminipro.infrastructure.users.controller;

//import com.ryanyovanda.backendminipro.usecase.referralPoints.impl.ReferralPointsUsecaseImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryanyovanda.backendminipro.common.response.Response;
import com.ryanyovanda.backendminipro.infrastructure.auth.Claims;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.CreateUserRequestDTO;
import com.ryanyovanda.backendminipro.usecase.user.CreateUserUsecase;
import com.ryanyovanda.backendminipro.usecase.user.GetUsersUseCase;

import lombok.extern.java.Log;

import java.util.List;

@Log
@RestController
@RequestMapping("/api/v1/users")
public class UsersPublicController {
  private final GetUsersUseCase getUsersUseCase;
  private final CreateUserUsecase createUserUsecase;
//  private final GetReferralPointsUseCase getReferralPointsUseCase;

  public UsersPublicController(final GetUsersUseCase getUsersUseCase, CreateUserUsecase createUserUsecase) {
    this.getUsersUseCase = getUsersUseCase;
    this.createUserUsecase = createUserUsecase;
  }

  //  Simple RBAC where only logged in admins are allowed to access get all users endpoint
  @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
  @GetMapping
  public ResponseEntity<?> getUsers() {
    String email = Claims.getEmailFromJwt();
    log.info("Requester email is: " + email);
    return Response.successfulResponse("Get all users success", getUsersUseCase.getAllUsers());
  }

//  @GetMapping("/discount")
//  public ResponseEntity<?> getUserDiscount() {
//    String email = Claims.getEmailFromJwt();
//    var user = CreateUserUsecase.getProfile(email);
//    if (user == null) {
//      return Response.failedResponse("User not found");
//    }
//    List<UserDiscount> userDiscounts = userDiscountService.getUserDiscounts(user.getId());
//    return Response.successfulResponse("User discounts", userDiscounts);
//  }

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
