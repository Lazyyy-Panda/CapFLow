package com.aaditya.capFlow.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateDistributionRequest(

        @NotNull(message = "Investor ID is required")
        Long investorId,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        BigDecimal amount,

        @NotNull(message = "Distribution date is required")
        LocalDate distributionDate
) {
}