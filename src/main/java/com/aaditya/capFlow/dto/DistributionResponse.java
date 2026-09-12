package com.aaditya.capFlow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DistributionResponse(
        Long id,
        Long fundId,
        Long investorId,
        BigDecimal amount,
        LocalDate distributionDate
) {
}