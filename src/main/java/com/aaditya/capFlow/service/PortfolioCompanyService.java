package com.aaditya.capFlow.service;

import com.aaditya.capFlow.repository.FundRepository;
import com.aaditya.capFlow.repository.PortfolioCompanyRepository;
import org.springframework.stereotype.Service;
import com.aaditya.capFlow.dto.CreatePortfolioCompanyRequest;
import com.aaditya.capFlow.entity.Fund;
import com.aaditya.capFlow.entity.PortfolioCompany;
import com.aaditya.capFlow.exception.ResourceNotFoundException;
import com.aaditya.capFlow.dto.PortfolioCompanyResponse;
import java.util.List;

@Service
public class PortfolioCompanyService {

    private final PortfolioCompanyRepository portfolioCompanyRepository;
    private final FundRepository fundRepository;

    public PortfolioCompanyService(
            PortfolioCompanyRepository portfolioCompanyRepository,
            FundRepository fundRepository
    ) {
        this.portfolioCompanyRepository = portfolioCompanyRepository;
        this.fundRepository = fundRepository;
    }

    public PortfolioCompanyResponse createCompany(
            Long fundId,
            CreatePortfolioCompanyRequest request
    ) {
        Fund fund = fundRepository.findById(fundId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fund not found with id: " + fundId
                        )
                );

        PortfolioCompany company = new PortfolioCompany(
                request.getName(),
                request.getSector(),
                fund
        );

        PortfolioCompany savedCompany =
                portfolioCompanyRepository.save(company);

        return new PortfolioCompanyResponse(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getSector(),
                savedCompany.getFund().getId()
        );
    }

    public List<PortfolioCompanyResponse> getCompaniesByFundId(Long fundId) {
        Fund fund = fundRepository.findById(fundId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fund not found with id: " + fundId
                        )
                );

        return portfolioCompanyRepository.findByFund(fund)
                .stream()
                .map(company -> new PortfolioCompanyResponse(
                        company.getId(),
                        company.getName(),
                        company.getSector(),
                        company.getFund().getId()
                ))
                .toList();
    }
}