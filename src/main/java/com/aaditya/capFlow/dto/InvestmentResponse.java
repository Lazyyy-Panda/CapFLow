package com.aaditya.capFlow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvestmentResponse(
        Long id,
        Long fundId,
        Long portfolioCompanyId,
        BigDecimal amount,
        LocalDate investmentDate
) {
}