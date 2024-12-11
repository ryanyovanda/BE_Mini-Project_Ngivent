//package com.example.referral.controller;
//
//import com.example.referral.entity.ReferralPoint;
//import com.example.referral.repository.ReferralPointRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/referral-points")
//public class ReferralPointsController {
//
//    @Autowired
//    private ReferralPointsRepository referralPointRepository;
//
//    @GetMapping
//    public List<ReferralPoint> getAllReferralPoints() {
//        return referralPointRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ReferralPoint> getReferralPointById(@PathVariable Integer id) {
//        Optional<ReferralPoint> referralPoint = referralPointRepository.findById(id);
//        return referralPoint.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ReferralPoint createReferralPoint(@RequestBody ReferralPoint referralPoint) {
//        return referralPointRepository.save(referralPoint);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ReferralPoint> updateReferralPoint(@PathVariable Integer id, @RequestBody ReferralPoint referralPointDetails) {
//        Optional<ReferralPoint> optionalReferralPoint = referralPointRepository.findById(id);
//        if (optionalReferralPoint.isPresent()) {
//            ReferralPoint referralPoint = optionalReferralPoint.get();
//            referralPoint.setPoint(referralPointDetails.getPoint());
//            referralPoint.setDescription(referralPointDetails.getDescription());
//            referralPoint.setExpiredAt(referralPointDetails.getExpiredAt());
//            ReferralPoint updatedReferralPoint = referralPointRepository.save(referralPoint);
//            return ResponseEntity.ok(updatedReferralPoint);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteReferralPoint(@PathVariable Integer id) {
//        if (referralPointRepository.existsById(id)) {
//            referralPointRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
