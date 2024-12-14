package com.ryanyovanda.backendminipro.infrastructure.analytic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketSoldData {
    private String timeUnit;
    private Long ticketsSold;
}
