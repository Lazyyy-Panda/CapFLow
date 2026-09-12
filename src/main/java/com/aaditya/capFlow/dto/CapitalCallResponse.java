package com.aaditya.capFlow.dto;

import com.aaditya.capFlow.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CapitalCallResponse(
        Long id,
        Long capitalCommitmentId,
        BigDecimal amount,
        LocalDate dueDate,
        PaymentStatus status
) {
}