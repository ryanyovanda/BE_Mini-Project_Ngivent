package com.ryanyovanda.backendminipro.usecase.user;

import com.ryanyovanda.backendminipro.entity.User;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.UserDetailResponseDTO;

import java.util.List;

public interface GetUsersUseCase {
  List<User> getAllUsers();
  UserDetailResponseDTO getUserById(Long id);
}
