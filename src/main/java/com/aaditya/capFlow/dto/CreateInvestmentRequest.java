package com.aaditya.capFlow.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateInvestmentRequest(
        @NotNull(message = "Portfolio company ID is required")
        Long portfolioCompanyId,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        BigDecimal amount,

        @NotNull(message = "Investment date is required")
        LocalDate investmentDate
) {
}