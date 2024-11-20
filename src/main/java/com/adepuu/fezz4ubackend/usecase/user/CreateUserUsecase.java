package com.adepuu.fezz4ubackend.usecase.user;

import com.adepuu.fezz4ubackend.entity.User;
import com.adepuu.fezz4ubackend.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.adepuu.fezz4ubackend.infrastructure.users.dto.CreateUserRequestDTO;
import com.adepuu.fezz4ubackend.infrastructure.users.dto.UserDetailResponseDTO;

import java.util.List;

public interface CreateUserUsecase {
  UserDetailResponseDTO createUser(CreateUserRequestDTO req);
  User createUserWithEntity(User req);
  List<User> bulkCreateUser(BulkCreateUserRequestDTO req);
}
