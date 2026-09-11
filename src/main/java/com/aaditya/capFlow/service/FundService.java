package com.aaditya.capFlow.service;

import com.aaditya.capFlow.dto.CreateFundRequest;
import com.aaditya.capFlow.dto.FundResponse;
import com.aaditya.capFlow.entity.Fund;
import com.aaditya.capFlow.repository.FundRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.aaditya.capFlow.exception.ResourceNotFoundException;

@Service
public class FundService {

    private final FundRepository fundRepository;

    public FundService(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    public FundResponse createFund(CreateFundRequest request) {

        Fund fund = new Fund(
                request.getName(),
                request.getVintageYear(),
                request.getTotalCommitment(),
                request.getCurrency(),
                request.getStatus()
        );

        Fund savedFund = fundRepository.save(fund);

        return new FundResponse(
                savedFund.getId(),
                savedFund.getName(),
                savedFund.getVintageYear(),
                savedFund.getTotalCommitment(),
                savedFund.getCurrency(),
                savedFund.getStatus()
        );
    }

    public List<FundResponse> getAllFunds() {
        return fundRepository.findAll()
                .stream()
                .map(fund -> new FundResponse(
                        fund.getId(),
                        fund.getName(),
                        fund.getVintageYear(),
                        fund.getTotalCommitment(),
                        fund.getCurrency(),
                        fund.getStatus()
                ))
                .toList();
    }

    public FundResponse getFundById(Long id) {
        Fund fund = fundRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Fund not found with id: " + id)
                );

        return new FundResponse(
                fund.getId(),
                fund.getName(),
                fund.getVintageYear(),
                fund.getTotalCommitment(),
                fund.getCurrency(),
                fund.getStatus()
        );
    }

    public FundResponse updateFund(Long id, CreateFundRequest request) {
        Fund fund = fundRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Fund not found with id: " + id)
                );

        fund.setName(request.getName());
        fund.setVintageYear(request.getVintageYear());
        fund.setTotalCommitment(request.getTotalCommitment());
        fund.setCurrency(request.getCurrency());
        fund.setStatus(request.getStatus());

        Fund updatedFund = fundRepository.save(fund);

        return new FundResponse(
                updatedFund.getId(),
                updatedFund.getName(),
                updatedFund.getVintageYear(),
                updatedFund.getTotalCommitment(),
                updatedFund.getCurrency(),
                updatedFund.getStatus()
        );
    }
}