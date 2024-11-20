package com.adepuu.fezz4ubackend.usecase.user.impl;

import com.adepuu.fezz4ubackend.entity.Role;
import com.adepuu.fezz4ubackend.entity.User;
import com.adepuu.fezz4ubackend.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.adepuu.fezz4ubackend.infrastructure.users.dto.CreateUserRequestDTO;
import com.adepuu.fezz4ubackend.infrastructure.users.dto.UserDetailResponseDTO;
import com.adepuu.fezz4ubackend.infrastructure.users.repository.RoleRepository;
import com.adepuu.fezz4ubackend.infrastructure.users.repository.UsersRepository;
import com.adepuu.fezz4ubackend.usecase.user.CreateUserUsecase;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log
@Service
public class CreateUserUsecaseImpl implements CreateUserUsecase {
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  public CreateUserUsecaseImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  @Override
  @CachePut(value = "userDetailResponseDTO", key = "#result.id", unless = "#result.isOnboardingFinished == true")
  public UserDetailResponseDTO createUser(CreateUserRequestDTO req){
    User newUser = req.toEntity();
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

    Optional<Role> defaultRole = roleRepository.findByName("USER");
    if (defaultRole.isPresent()) {
        newUser.getRoles().add(defaultRole.get());
    } else {
      throw new RuntimeException("Default role not found");
    }
    var savedUser = usersRepository.save(newUser);
    return new UserDetailResponseDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getProfilePictureUrl(), savedUser.getIsOnboardingFinished());
  }

  @Override
  public User createUserWithEntity(User req) {
    return null;
  }

  @Override
  @Transactional
  public List<User> bulkCreateUser(BulkCreateUserRequestDTO req) {
    List<User> usersList = req.getUsers().stream().map(CreateUserRequestDTO::toEntity).toList();
    usersRepository.saveAll(usersList);
    return usersList;
  }
}
