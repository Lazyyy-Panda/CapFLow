package com.aaditya.capFlow.service;

import com.aaditya.capFlow.dto.CreateDistributionRequest;
import com.aaditya.capFlow.dto.DistributionResponse;
import com.aaditya.capFlow.entity.Distribution;
import com.aaditya.capFlow.entity.Fund;
import com.aaditya.capFlow.entity.Investor;
import com.aaditya.capFlow.exception.ResourceNotFoundException;
import com.aaditya.capFlow.repository.DistributionRepository;
import com.aaditya.capFlow.repository.FundRepository;
import com.aaditya.capFlow.repository.InvestorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionService {

    private final FundRepository fundRepository;
    private final InvestorRepository investorRepository;
    private final DistributionRepository distributionRepository;

    public DistributionService(
            FundRepository fundRepository,
            InvestorRepository investorRepository,
            DistributionRepository distributionRepository
    ) {
        this.fundRepository = fundRepository;
        this.investorRepository = investorRepository;
        this.distributionRepository = distributionRepository;
    }

    public DistributionResponse createDistribution(
            Long fundId,
            CreateDistributionRequest request
    ) {
        Fund fund = fundRepository.findById(fundId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Fund not found with id " + fundId
                ));

        Investor investor = investorRepository.findById(request.investorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Investor not found with id " + request.investorId()
                ));

        Distribution distribution = new Distribution(
                fund,
                investor,
                request.amount(),
                request.distributionDate()
        );

        Distribution savedDistribution = distributionRepository.save(distribution);

        return new DistributionResponse(
                savedDistribution.getId(),
                savedDistribution.getFund().getId(),
                savedDistribution.getInvestor().getId(),
                savedDistribution.getAmount(),
                savedDistribution.getDistributionDate()
        );
    }

    public List<DistributionResponse> getDistributionsByFundId(Long fundId) {
        if (!fundRepository.existsById(fundId)) {
            throw new ResourceNotFoundException(
                    "Fund not found with id " + fundId
            );
        }

        return distributionRepository.findByFundId(fundId)
                .stream()
                .map(distribution -> new DistributionResponse(
                        distribution.getId(),
                        distribution.getFund().getId(),
                        distribution.getInvestor().getId(),
                        distribution.getAmount(),
                        distribution.getDistributionDate()
                ))
                .toList();
    }
}