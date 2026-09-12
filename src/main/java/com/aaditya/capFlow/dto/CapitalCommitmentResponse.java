package com.aaditya.capFlow.dto;

import java.math.BigDecimal;

public record CapitalCommitmentResponse(
        Long id,
        Long fundId,
        Long investorId,
        BigDecimal amount
) {
}