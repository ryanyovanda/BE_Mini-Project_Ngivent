//package com.ryanyovanda.backendminipro.infrastructure.referralPoints.repository;
//
//
//import com.ryanyovanda.backendminipro.entity.ReferralPoint;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface ReferralPointsRepository extends JpaRepository<ReferralPoint,Integer> {
//    Optional<ReferralPoint findByCode(String code);
//    List<ReferralPoint> findReferralPointsByUserId(Integer userId);
//    @Query("SELECT ud FROM UserDiscount ud WHERE ud.userId = :userId AND ud.isUsed = false AND (ud.expiredAt IS NULL OR ud.expiredAt > CURRENT_TIMESTAMP)")
//    List<ReferralPoint> findActiveReferralPointsByUserId(@Param("userId") Integer userId);
//}
