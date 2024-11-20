package com.adepuu.fezz4ubackend.usecase.user.impl;

import com.adepuu.fezz4ubackend.common.exceptions.DataNotFoundException;
import com.adepuu.fezz4ubackend.entity.User;
import com.adepuu.fezz4ubackend.infrastructure.users.dto.UserDetailResponseDTO;
import com.adepuu.fezz4ubackend.infrastructure.users.repository.UsersRepository;
import com.adepuu.fezz4ubackend.usecase.user.GetUsersUseCase;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserUseCaseImpl implements GetUsersUseCase {
  private final UsersRepository usersRepository;

  public GetUserUseCaseImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public List<User> getAllUsers() {
    return usersRepository.findAll();
  }

  @Override
  @Cacheable(value = "userDetailResponseDTO", key = "#id", unless = "#result.isOnboardingFinished == true")
  public UserDetailResponseDTO getUserById(Long id) {
    var foundUser = usersRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
    return new UserDetailResponseDTO(foundUser.getId(), foundUser.getEmail(), foundUser.getProfilePictureUrl(), foundUser.getIsOnboardingFinished());
  }
}
