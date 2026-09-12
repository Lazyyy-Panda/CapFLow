package com.aaditya.capFlow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CapitalCallPaymentResponse(
        Long id,
        Long capitalCallId,
        BigDecimal amount,
        LocalDate paymentDate
) {
}