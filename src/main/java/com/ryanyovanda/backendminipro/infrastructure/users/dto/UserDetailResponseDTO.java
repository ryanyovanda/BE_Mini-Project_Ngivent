package com.ryanyovanda.backendminipro.infrastructure.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponseDTO implements Serializable {
  private Long id;
  private String email;
  private String profilePictureUrl;
  private Boolean isOnboardingFinished = false;
  private String referralCode; // Add this to show the user's referral code
  private Long referrerId; // Add this to show the referrer ID if used
}

