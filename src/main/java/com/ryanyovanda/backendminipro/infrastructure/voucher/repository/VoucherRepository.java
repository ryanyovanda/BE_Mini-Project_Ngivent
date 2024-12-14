package com.ryanyovanda.backendminipro.infrastructure.voucher.repository;

import com.ryanyovanda.backendminipro.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
}
