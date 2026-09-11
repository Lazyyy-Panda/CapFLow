package com.aaditya.capFlow.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "funds")
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "vintage_year", nullable = false)
    private Integer vintageYear;

    @Column(name = "total_commitment", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalCommitment;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private String status;

    public Fund() {
    }

    public Fund(
            String name,
            Integer vintageYear,
            BigDecimal totalCommitment,
            String currency,
            String status
    ) {
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