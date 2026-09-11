package com.aaditya.capFlow.dto;

import java.math.BigDecimal;

public class FundResponse {

    private Long id;
    private String name;
    private Integer vintageYear;
    private BigDecimal totalCommitment;
    private String currency;
    private String status;

    public FundResponse(
            Long id,
            String name,
            Integer vintageYear,
            BigDecimal totalCommitment,
            String currency,
            String status
    ) {
        this.id = id;
        this.name = name;
        this.vintageYear = vintageYear;
        this.totalCommitment = totalCommitment;
        this.currency = currency;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getVintageYear() {
        return vintageYear;
    }

    public BigDecimal getTotalCommitment() {
        return totalCommitment;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }
}