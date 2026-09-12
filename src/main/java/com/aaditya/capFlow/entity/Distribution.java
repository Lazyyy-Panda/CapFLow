package com.aaditya.capFlow.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "distributions")
public class Distribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fund_id", nullable = false)
    private Fund fund;

    @ManyToOne
    @JoinColumn(name = "investor_id", nullable = false)
    private Investor investor;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate distributionDate;

    public Distribution() {
    }

    public Distribution(
            Fund fund,
            Investor investor,
            BigDecimal amount,
            LocalDate distributionDate
    ) {
        this.fund = fund;
        this.investor = investor;
        this.amount = amount;
        this.distributionDate = distributionDate;
    }

    public Long getId() {
        return id;
    }

    public Fund getFund() {
        return fund;
    }

    public Investor getInvestor() {
        return investor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDistributionDate() {
        return distributionDate;
    }
}