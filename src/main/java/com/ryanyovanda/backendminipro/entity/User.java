package com.ryanyovanda.backendminipro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
  @SequenceGenerator(name = "users_id_gen", sequenceName = "users_user_id_seq", allocationSize = 1)
  @Column(name = "user_id", nullable = false)
  private Long id;

  @Size(max = 50)
  @NotNull
  @Column(name = "email", nullable = false, length = 50)
  private String email;

  @NotNull
  @Column(name = "password", nullable = false, length = 50)
  private String password;

  @Size(max = 50)
  @NotNull
  @Column(name = "pin", nullable = false, length = 50)
  private String pin;

  @Size(max = 100)
  @Column(name = "profile_picture_url", length = 100)
  private String profilePictureUrl;

  @NotNull
  @ColumnDefault("false")
  @Column(name = "is_onboarding_finished", nullable = false)
  private Boolean isOnboardingFinished = false;

  @Size(max = 50)
  @Column(name = "referral_code", length = 50)
  private String referralCode;

  @Column(name = "used_referral_code", length = Integer.MAX_VALUE)
  private String usedReferralCode;

//  @NotNull
//  @ColumnDefault("false")
//  @Column(name = "is_first_time_discount", nullable = false)
//  private Boolean isFirstTimeDiscount = false;

  @NotNull
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @NotNull
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  @Column(name = "deleted_at")
  private OffsetDateTime deletedAt;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

//  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//  @JoinTable(name = "user_point", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "point_id"))
//  private Set<Role> roles = new HashSet<>();

  @PrePersist
  protected void onCreate() {
    createdAt = OffsetDateTime.now();
    updatedAt = OffsetDateTime.now();
    this.referralCode = UUID.randomUUID().toString();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = OffsetDateTime.now();
  }

  @PreRemove
  protected void onRemove() {
    deletedAt = OffsetDateTime.now();
  }

  public Boolean isOrganizer() {
    return roles.stream().anyMatch(role -> role.getName().equals("ORGANIZER"));
  }
}