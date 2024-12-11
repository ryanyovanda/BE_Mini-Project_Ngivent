package com.ryanyovanda.backendminipro.usecase.analytic;

import java.time.OffsetDateTime;
import java.util.List;

public interface GenerateTimeUnitsUseCase {
    List<String> generateTimeUnits(OffsetDateTime start, OffsetDateTime end, Integer range);
}
