package com.ryanyovanda.backendminipro.infrastructure.analytic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RevenueData {
    private String timeUnit;
    private Double revenue;


}
