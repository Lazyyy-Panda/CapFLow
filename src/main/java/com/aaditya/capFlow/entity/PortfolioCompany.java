package com.aaditya.capFlow.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "portfolio_companies")
public class PortfolioCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sector;

    @ManyToOne
    @JoinColumn(name = "fund_id", nullable = false)
    private Fund fund;

    public PortfolioCompany() {
    }

    public PortfolioCompany(String name, String sector, Fund fund) {
        this.name = name;
        this.sector = sector;
        this.fund = fund;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSector() {
        return sector;
    }

    public Fund getFund() {
        return fund;
    }
}