package com.ryanyovanda.backendminipro.usecase.user;

import com.ryanyovanda.backendminipro.entity.User;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.CreateUserRequestDTO;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.UserDetailResponseDTO;

import java.util.List;

public interface CreateUserUsecase {
  UserDetailResponseDTO createUser(CreateUserRequestDTO req); // Main user creation logic

  User createUserWithEntity(User req); // Allows user creation from User entity

  List<User> bulkCreateUser(BulkCreateUserRequestDTO req); // Handles bulk user creation

  User getUserByReferralCode(String referralCode); // Retrieves user by referral code

  void addVoucher(Long userId, String type, int value); // Adds a voucher for a user
}
