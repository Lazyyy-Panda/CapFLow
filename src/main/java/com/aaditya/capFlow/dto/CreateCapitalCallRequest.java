package com.aaditya.capFlow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateCapitalCallRequest(
        BigDecimal amount,
        LocalDate dueDate
) {
}