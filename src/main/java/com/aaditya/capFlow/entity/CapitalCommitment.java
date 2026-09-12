package com.aaditya.capFlow.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "capital_commitments")
public class CapitalCommitment {

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

    public CapitalCommitment() {
    }

    public CapitalCommitment(
            Fund fund,
            Investor investor,
            BigDecimal amount
    ) {
        this.fund = fund;
        this.investor = investor;
        this.amount = amount;
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
}