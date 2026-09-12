package com.aaditya.capFlow.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fund_id", nullable = false)
    private Fund fund;

    @ManyToOne
    @JoinColumn(name = "portfolio_company_id", nullable = false)
    private PortfolioCompany portfolioCompany;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate investmentDate;

    public Investment() {
    }

    public Investment(
            Fund fund,
            PortfolioCompany portfolioCompany,
            BigDecimal amount,
            LocalDate investmentDate
    ) {
        this.fund = fund;
        this.portfolioCompany = portfolioCompany;
        this.amount = amount;
        this.investmentDate = investmentDate;
    }

    public Long getId() {
        return id;
    }

    public Fund getFund() {
        return fund;
    }

    public PortfolioCompany getPortfolioCompany() {
        return portfolioCompany;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getInvestmentDate() {
        return investmentDate;
    }
}