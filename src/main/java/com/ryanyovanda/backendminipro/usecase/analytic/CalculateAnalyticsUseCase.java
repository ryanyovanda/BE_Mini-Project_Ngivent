package com.ryanyovanda.backendminipro.usecase.analytic;

import com.ryanyovanda.backendminipro.infrastructure.analytic.dto.AnalyticsRespond;


public interface CalculateAnalyticsUseCase {
    AnalyticsRespond calculateAnalytics(Integer range, Integer email);
}