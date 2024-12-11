package com.ryanyovanda.backendminipro.usecase.analytic.Impl;

import com.ryanyovanda.backendminipro.infrastructure.analytic.dto.AnalyticsRespond;
import com.ryanyovanda.backendminipro.infrastructure.analytic.dto.RevenueData;
import com.ryanyovanda.backendminipro.infrastructure.analytic.dto.TicketSoldData;
import com.ryanyovanda.backendminipro.infrastructure.events.repository.EventRepository;
import com.ryanyovanda.backendminipro.infrastructure.ticket.repository.TicketRepository;
import com.ryanyovanda.backendminipro.infrastructure.transaction.repository.TransactionRepository;
import com.ryanyovanda.backendminipro.usecase.analytic.CalculateAnalyticsUseCase;
import com.ryanyovanda.backendminipro.usecase.analytic.GenerateTimeUnitsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CalculateAnalyticsUseCaseImpl implements CalculateAnalyticsUseCase {

    private final TransactionRepository transactionRepository;
    private final GenerateTimeUnitsUseCase generateTimeUnitsUseCase;

    @Override
    public AnalyticsRespond calculateAnalytics(Integer range, Integer userId) {
        AnalyticsRespond response = new AnalyticsRespond();

        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime start;
        OffsetDateTime end;

        List<Object[]> results;

        // Define range (daily or monthly)
        if (range == 1) { // Daily analytics
            start = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            end = start.plusMonths(1);
            results = transactionRepository.getDailyRevenue(userId, start, end);
        } else if (range == 2) { // Monthly analytics
            start = now.withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            end = start.plusYears(1);
            results = transactionRepository.getMonthlyRevenue(userId, start, end);
        } else {
            throw new IllegalArgumentException("Invalid range: Use 1 (Daily) or 2 (Monthly).");
        }

        // Aggregate results
        Double totalRevenue = 0.0;
        long totalTicketsSold = 0;

        Map<String, Long> ticketsSoldMap = new HashMap<>();
        Map<String, Double> revenueMap = new HashMap<>();

        for (Object[] row : results) {
            String timeUnit = (String) row[2]; // Date or Month
            long ticketsSold = ((Number) row[1]).longValue(); // Ticket count
            double revenue = ((Number) row[0]).doubleValue(); // Revenue

            totalTicketsSold += ticketsSold;
            totalRevenue += revenue;

            ticketsSoldMap.put(timeUnit, ticketsSold);
            revenueMap.put(timeUnit, revenue);
        }

        // Generate time units (days or months)
        List<String> timeUnits = generateTimeUnitsUseCase.generateTimeUnits(start, end, range);

        // Map results to response format
        List<TicketSoldData> ticketSoldData = timeUnits.stream()
                .map(unit -> new TicketSoldData(unit, ticketsSoldMap.getOrDefault(unit, 0L)))
                .toList();

        List<RevenueData> revenueData = timeUnits.stream()
                .map(unit -> new RevenueData(unit, revenueMap.getOrDefault(unit, 0.0)))
                .toList();

        // Set response data
        response.setTotalTicketsSold(totalTicketsSold);
        response.setTotalRevenue(totalRevenue);
        response.setTicketSoldData(ticketSoldData);
        response.setRevenueData(revenueData);

        return response;
    }
}
