package com.aaditya.capFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateInvestorRequest(
        @NotBlank(message = "Investor name must not be blank")
        String name
) {
}