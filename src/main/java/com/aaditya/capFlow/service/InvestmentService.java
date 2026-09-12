package com.aaditya.capFlow.service;

import com.aaditya.capFlow.repository.FundRepository;
import com.aaditya.capFlow.repository.InvestmentRepository;
import com.aaditya.capFlow.repository.PortfolioCompanyRepository;
import org.springframework.stereotype.Service;
import com.aaditya.capFlow.dto.CreateInvestmentRequest;
import com.aaditya.capFlow.dto.InvestmentResponse;
import com.aaditya.capFlow.entity.Fund;
import com.aaditya.capFlow.entity.Investment;
import com.aaditya.capFlow.entity.PortfolioCompany;
import java.util.List;
import com.aaditya.capFlow.exception.ResourceNotFoundException;

@Service
public class InvestmentService {

    private final FundRepository fundRepository;
    private final PortfolioCompanyRepository portfolioCompanyRepository;
    private final InvestmentRepository investmentRepository;

    public InvestmentService(
            FundRepository fundRepository,
            PortfolioCompanyRepository portfolioCompanyRepository,
            InvestmentRepository investmentRepository
    ) {
        this.fundRepository = fundRepository;
        this.portfolioCompanyRepository = portfolioCompanyRepository;
        this.investmentRepository = investmentRepository;
    }

    public InvestmentResponse createInvestment(
            Long fundId,
            CreateInvestmentRequest request
    ) {
        Fund fund = fundRepository.findById(fundId)
                .orElseThrow(() -> new ResourceNotFoundException("Fund not found"));

        PortfolioCompany portfolioCompany = portfolioCompanyRepository
                .findById(request.portfolioCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio company not found"));

        Investment investment = new Investment(
                fund,
                portfolioCompany,
                request.amount(),
                request.investmentDate()
        );

        Investment savedInvestment = investmentRepository.save(investment);

        return new InvestmentResponse(
                savedInvestment.getId(),
                savedInvestment.getFund().getId(),
                savedInvestment.getPortfolioCompany().getId(),
                savedInvestment.getAmount(),
                savedInvestment.getInvestmentDate()
        );
    }

    public List<InvestmentResponse> getInvestmentsByFundId(Long fundId) {
        if (!fundRepository.existsById(fundId)) {
            throw new ResourceNotFoundException("Fund not found");
        }

        return investmentRepository.findByFundId(fundId)
                .stream()
                .map(investment -> new InvestmentResponse(
                        investment.getId(),
                        investment.getFund().getId(),
                        investment.getPortfolioCompany().getId(),
                        investment.getAmount(),
                        investment.getInvestmentDate()
                ))
                .toList();
    }
}