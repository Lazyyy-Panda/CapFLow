package com.aaditya.capFlow.controller;

import com.aaditya.capFlow.dto.CreatePortfolioCompanyRequest;
import com.aaditya.capFlow.dto.PortfolioCompanyResponse;
import com.aaditya.capFlow.service.PortfolioCompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/api/funds/{fundId}/companies")
public class PortfolioCompanyController {

    private final PortfolioCompanyService portfolioCompanyService;

    public PortfolioCompanyController(
            PortfolioCompanyService portfolioCompanyService
    ) {
        this.portfolioCompanyService = portfolioCompanyService;
    }

    @PostMapping
    public ResponseEntity<PortfolioCompanyResponse> createCompany(
            @PathVariable Long fundId,
            @Valid @RequestBody CreatePortfolioCompanyRequest request
    ) {
        PortfolioCompanyResponse response =
                portfolioCompanyService.createCompany(fundId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PortfolioCompanyResponse>> getCompaniesByFundId(
            @PathVariable Long fundId
    ) {
        List<PortfolioCompanyResponse> companies =
                portfolioCompanyService.getCompaniesByFundId(fundId);

        return ResponseEntity.ok(companies);
    }
}