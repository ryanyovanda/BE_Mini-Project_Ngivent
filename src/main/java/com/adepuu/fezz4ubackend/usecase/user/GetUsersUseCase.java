package com.adepuu.fezz4ubackend.usecase.user;

import com.adepuu.fezz4ubackend.entity.User;
import com.adepuu.fezz4ubackend.infrastructure.users.dto.UserDetailResponseDTO;

import java.util.List;

public interface GetUsersUseCase {
  List<User> getAllUsers();
  UserDetailResponseDTO getUserById(Long id);
}
