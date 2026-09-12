package com.aaditya.capFlow.dto;

public class PortfolioCompanyResponse {

    private Long id;
    private String name;
    private String sector;
    private Long fundId;

    public PortfolioCompanyResponse(
            Long id,
            String name,
            String sector,
            Long fundId
    ) {
        this.id = id;
        this.name = name;
        this.sector = sector;
        this.fundId = fundId;
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

    public Long getFundId() {
        return fundId;
    }
}