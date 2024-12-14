package com.ryanyovanda.backendminipro.usecase.user.impl;

import com.ryanyovanda.backendminipro.entity.Role;
import com.ryanyovanda.backendminipro.entity.User;
import com.ryanyovanda.backendminipro.entity.Voucher;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.BulkCreateUserRequestDTO;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.CreateUserRequestDTO;
import com.ryanyovanda.backendminipro.infrastructure.users.dto.UserDetailResponseDTO;
import com.ryanyovanda.backendminipro.infrastructure.users.repository.RoleRepository;
import com.ryanyovanda.backendminipro.infrastructure.users.repository.UsersRepository;
import com.ryanyovanda.backendminipro.infrastructure.voucher.repository.VoucherRepository;
import com.ryanyovanda.backendminipro.usecase.user.CreateUserUsecase;
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
  private final VoucherRepository voucherRepository;

  public CreateUserUsecaseImpl(UsersRepository usersRepository,
                               PasswordEncoder passwordEncoder,
                               RoleRepository roleRepository,
                               VoucherRepository voucherRepository) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
    this.voucherRepository = voucherRepository;
  }

  @Override
  @CachePut(value = "userDetailResponseDTO", key = "#result.id", unless = "#result.isOnboardingFinished == true")
  public UserDetailResponseDTO createUser(CreateUserRequestDTO req) {
    // Create the new user entity
    User newUser = req.toEntity();
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

    // Assign default role
    Role defaultRole = roleRepository.findByName("USER")
            .orElseThrow(() -> new RuntimeException("Default role not found"));
    newUser.getRoles().add(defaultRole);

    // Handle referral code if provided
    if (req.getReferralCode() != null) {
      Optional<User> referrerOpt = usersRepository.findByReferralCode(req.getReferralCode());
      if (referrerOpt.isEmpty()) {
        throw new RuntimeException("Invalid referral code");
      }
      User referrer = referrerOpt.get();

      // Prevent self-referral
      if (referrer.getId().equals(newUser.getId())) {
        throw new RuntimeException("Cannot use your own referral code");
      }

      // Associate referrer with the new user
      newUser.setReferrer(referrer);
    }

    // Save the new user
    var savedUser = usersRepository.save(newUser);

    // Grant vouchers if referral code was used
    if (newUser.getReferrer() != null) {
      grantReferralVouchers(newUser.getReferrer(), savedUser);
    }

    // Return user details
    return new UserDetailResponseDTO(savedUser.getId(), savedUser.getEmail(),
            savedUser.getProfilePictureUrl(),
            savedUser.getIsOnboardingFinished(),
            savedUser.getReferralCode(), // User's referral code
            savedUser.getReferrer() != null ? savedUser.getReferrer().getId() : null // Referrer ID, if exists
    );
  }

  @Override
  public User createUserWithEntity(User req) {
    return usersRepository.save(req);
  }

  @Override
  @Transactional
  public List<User> bulkCreateUser(BulkCreateUserRequestDTO req) {
    List<User> usersList = req.getUsers().stream().map(CreateUserRequestDTO::toEntity).toList();
    usersRepository.saveAll(usersList);
    return usersList;
  }

  @Override
  public User getUserByReferralCode(String referralCode) {
    return usersRepository.findByReferralCode(referralCode)
            .orElseThrow(() -> new RuntimeException("Referral code not found"));
  }

  private void grantReferralVouchers(User referrer, User newUser) {
    // Grant voucher to the referrer (10% discount)
    addVoucher(referrer.getId(), "discount", 10);

    // Grant voucher to the new user (10,000 points)
    addVoucher(newUser.getId(), "points", 10000);
  }

  @Override
  public void addVoucher(Long userId, String type, int value) {
    User user = usersRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Voucher voucher = new Voucher();
    voucher.setUser(user);
    voucher.setType(type);
    voucher.setValue(value);
    voucherRepository.save(voucher);
  }
}
