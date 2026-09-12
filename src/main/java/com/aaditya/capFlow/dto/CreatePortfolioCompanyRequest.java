package com.aaditya.capFlow.dto;

import jakarta.validation.constraints.NotBlank;

public class CreatePortfolioCompanyRequest {

    @NotBlank(message = "Company name is required")
    private String name;

    @NotBlank(message = "Sector is required")
    private String sector;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}