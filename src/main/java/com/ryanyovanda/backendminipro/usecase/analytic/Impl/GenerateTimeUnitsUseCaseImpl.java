package com.ryanyovanda.backendminipro.usecase.analytic.Impl;

import com.ryanyovanda.backendminipro.usecase.analytic.GenerateTimeUnitsUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateTimeUnitsUseCaseImpl implements GenerateTimeUnitsUseCase {

    @Override
    public List<String> generateTimeUnits(OffsetDateTime start, OffsetDateTime end, Integer range) {
        List<String> timeUnits = new ArrayList<>();

        // Validate range
        if (range != 1 && range != 2) {
            throw new IllegalArgumentException("Invalid range: must be 1 (daily) or 2 (monthly).");
        }

        // Generate daily units
        if (range == 1) {
            LocalDate startDate = start.toLocalDate();
            LocalDate endDate = end.toLocalDate();

            LocalDate currentDay = startDate;
            while (!currentDay.isAfter(endDate)) {
                timeUnits.add(currentDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                currentDay = currentDay.plusDays(1);
            }
        }
        // Generate monthly units
        else if (range == 2) {
            LocalDate startDate = start.toLocalDate();
            LocalDate endDate = end.toLocalDate();

            LocalDate currentMonth = startDate.withDayOfMonth(1);
            while (!currentMonth.isAfter(endDate)) {
                timeUnits.add(currentMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")));
                currentMonth = currentMonth.plusMonths(1);
            }
        }

        return timeUnits;
    }
}
