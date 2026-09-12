package com.aaditya.capFlow.service;

import com.aaditya.capFlow.dto.CapitalCommitmentResponse;
import com.aaditya.capFlow.dto.CreateCapitalCommitmentRequest;
import com.aaditya.capFlow.entity.CapitalCommitment;
import com.aaditya.capFlow.entity.Fund;
import com.aaditya.capFlow.entity.Investor;
import com.aaditya.capFlow.repository.CapitalCommitmentRepository;
import com.aaditya.capFlow.repository.FundRepository;
import com.aaditya.capFlow.repository.InvestorRepository;
import org.springframework.stereotype.Service;
import com.aaditya.capFlow.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CapitalCommitmentService {

    private final FundRepository fundRepository;
    private final InvestorRepository investorRepository;
    private final CapitalCommitmentRepository capitalCommitmentRepository;

    public CapitalCommitmentService(
            FundRepository fundRepository,
            InvestorRepository investorRepository,
            CapitalCommitmentRepository capitalCommitmentRepository
    ) {
        this.fundRepository = fundRepository;
        this.investorRepository = investorRepository;
        this.capitalCommitmentRepository = capitalCommitmentRepository;
    }

    public CapitalCommitmentResponse createCommitment(
            Long fundId,
            CreateCapitalCommitmentRequest request
    ) {
        Fund fund = fundRepository.findById(fundId)
                .orElseThrow(() -> new ResourceNotFoundException("Fund not found"));

        Investor investor = investorRepository.findById(request.investorId())
                .orElseThrow(() -> new ResourceNotFoundException("Investor not found"));

        CapitalCommitment commitment = new CapitalCommitment(
                fund,
                investor,
                request.amount()
        );

        CapitalCommitment savedCommitment =
                capitalCommitmentRepository.save(commitment);

        return new CapitalCommitmentResponse(
                savedCommitment.getId(),
                savedCommitment.getFund().getId(),
                savedCommitment.getInvestor().getId(),
                savedCommitment.getAmount()
        );
    }

    public List<CapitalCommitmentResponse> getCommitmentsByFundId(Long fundId) {
        if (!fundRepository.existsById(fundId)) {
            throw new ResourceNotFoundException("Fund not found");
        }

        return capitalCommitmentRepository.findByFundId(fundId)
                .stream()
                .map(commitment -> new CapitalCommitmentResponse(
                        commitment.getId(),
                        commitment.getFund().getId(),
                        commitment.getInvestor().getId(),
                        commitment.getAmount()
                ))
                .toList();
    }
}