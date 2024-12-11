package com.ryanyovanda.backendminipro.usecase.auth.impl;

import com.ryanyovanda.backendminipro.common.exceptions.DataNotFoundException;
import com.ryanyovanda.backendminipro.entity.User;
import com.ryanyovanda.backendminipro.infrastructure.auth.dto.UserAuth;
import com.ryanyovanda.backendminipro.infrastructure.users.repository.UsersRepository;
import com.ryanyovanda.backendminipro.usecase.auth.GetUserAuthDetailsUsecase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetUserAuthDetailsUsecaseImpl implements GetUserAuthDetailsUsecase {
  private final UsersRepository usersRepository;

  public GetUserAuthDetailsUsecaseImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User existingUser = usersRepository.findByEmailContainsIgnoreCase(username).orElseThrow(() -> new DataNotFoundException("User not found with email: " + username));

    UserAuth userAuth = new UserAuth();
    userAuth.setUser(existingUser);
    return userAuth;
  }
}
