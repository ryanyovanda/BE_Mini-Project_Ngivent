//package com.ryanyovanda.backendminipro.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.ColumnDefault;
//
//
//import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.util.UUID;
//
//@Entity
//@Setter
//@Getter
//@Table(name = "referral_points")
//public class ReferralPoint {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "point_id_gen")
//    @SequenceGenerator(name = "point_id_gen", sequenceName = "point__id_seq", allocationSize = 1)
//    @Column(name = "point_id", nullable = false)
//    private Integer id;
//
//    @Column(name = "user_id", nullable = false)
//    private Integer userId;
//
//    @NotNull
//    @Column(name = "point", nullable = false)
//    private Integer point;
//
//    @NotNull
//    @Column(name = "description", nullable = false)
//    private String description = "Point acquired from referral code"; // Default value untuk description
//
//    @Column(name = "is_percentage")
//    private Boolean isPercentage;
//
//    @Column(name = "is_used")
//    private Boolean isUsed;
//
//    @ColumnDefault("CURRENT_TIMESTAMP")
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private OffsetDateTime createdAt;
//
//    @ColumnDefault("CURRENT_TIMESTAMP")
//    @Column(name = "updated_at", nullable = false)
//    private OffsetDateTime updatedAt;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "deleted_at")
//    private OffsetDateTime deletedAt;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "expired_at")
//    private OffsetDateTime expiredAt;
//
//    @NotNull
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = OffsetDateTime.now();
//        updatedAt = OffsetDateTime.now();
//        expiredAt = OffsetDateTime.now().plusDays(90);
//    }
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = OffsetDateTime.now();
//    }
//
//    @PreRemove
//    protected void onRemove() {
//        deletedAt = OffsetDateTime.now();
//    }
//}