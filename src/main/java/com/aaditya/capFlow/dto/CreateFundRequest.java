package com.aaditya.capFlow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CreateFundRequest {

    @NotBlank(message = "Fund name is required")
    private String name;

    @NotNull(message = "Vintage year is required")
    private Integer vintageYear;

    @NotNull(message = "Total commitment is required")
    @Positive(message = "Total commitment must be positive")
    private BigDecimal totalCommitment;

    @NotBlank(message = "Currency is required")
    private String currency;

    @NotBlank(message = "Status is required")
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVintageYear() {
        return vintageYear;
    }

    public void setVintageYear(Integer vintageYear) {
        this.vintageYear = vintageYear;
    }

    public BigDecimal getTotalCommitment() {
        return totalCommitment;
    }

    public void setTotalCommitment(BigDecimal totalCommitment) {
        this.totalCommitment = totalCommitment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}