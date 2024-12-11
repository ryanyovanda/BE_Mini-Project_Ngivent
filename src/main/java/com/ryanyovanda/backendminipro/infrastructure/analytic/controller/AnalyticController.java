package com.ryanyovanda.backendminipro.infrastructure.analytic.controller;

import com.ryanyovanda.backendminipro.entity.User;
import com.ryanyovanda.backendminipro.infrastructure.users.repository.UsersRepository;
import com.ryanyovanda.backendminipro.usecase.analytic.CalculateAnalyticsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/analytic")
@RequiredArgsConstructor
public class AnalyticController {

    private final CalculateAnalyticsUseCase calculateAnalyticsUseCase;
    private final UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<?> getAnalytics(@RequestParam Integer range, @RequestParam String email) {
        // Retrieve user entity from the email
        Optional<User> optionalUser = usersRepository.findByEmailContainsIgnoreCase(email);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid email or user not found");
        }

        // Extract userId from the User object and convert it to Integer
        Long userIdLong = optionalUser.get().getId();
        Integer userId = Math.toIntExact(userIdLong);

        // Call the use case to calculate analytics
        var analyticsResult = calculateAnalyticsUseCase.calculateAnalytics(range, userId);

        // Return the response
        return ResponseEntity.ok(analyticsResult);
    }
}
