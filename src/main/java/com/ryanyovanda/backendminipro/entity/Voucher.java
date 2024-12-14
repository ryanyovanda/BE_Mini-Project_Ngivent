package com.ryanyovanda.backendminipro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to User
    private User user; // Associate with User entity

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "value", nullable = false)
    private int value;

    @Column(name = "is_used", nullable = false)
    private boolean isUsed = false;
}
