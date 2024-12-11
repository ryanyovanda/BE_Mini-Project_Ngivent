package com.ryanyovanda.backendminipro.usecase.user;

import com.ryanyovanda.backendminipro.entity.User;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.CreateUserRequestDTO;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.UserDetailResponseDTO;

import java.util.List;

public interface CreateUserUsecase {
  UserDetailResponseDTO createUser(CreateUserRequestDTO req);
  User createUserWithEntity(User req);
  List<User> bulkCreateUser(BulkCreateUserRequestDTO req);
}
