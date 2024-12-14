package com.ryanyovanda.backendminipro.usecase.user.impl;

import com.ryanyovanda.backendminipro.common.exceptions.DataNotFoundException;
import com.ryanyovanda.backendminipro.entity.User;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.UserDetailResponseDTO;
import com.ryanyovanda.backendminipro.infrastructure.users.repository.UsersRepository;
import com.ryanyovanda.backendminipro.usecase.user.GetUsersUseCase;

import org.springframework.cache.annotation.Cacheable;
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
    return new UserDetailResponseDTO(foundUser.getId(), foundUser.getEmail(), foundUser.getProfilePictureUrl(), foundUser.getIsOnboardingFinished(), foundUser.getReferralCode(), foundUser.getReferrer() != null ? foundUser.getReferrer().getId() : null);
  }
}
