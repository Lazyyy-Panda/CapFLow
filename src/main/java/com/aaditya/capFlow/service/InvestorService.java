package com.aaditya.capFlow.service;

import com.aaditya.capFlow.dto.CreateInvestorRequest;
import com.aaditya.capFlow.dto.InvestorResponse;
import com.aaditya.capFlow.entity.Investor;
import com.aaditya.capFlow.repository.InvestorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public InvestorResponse createInvestor(CreateInvestorRequest request) {
        Investor investor = new Investor(request.name());

        Investor savedInvestor = investorRepository.save(investor);

        return new InvestorResponse(
                savedInvestor.getId(),
                savedInvestor.getName()
        );
    }

    public List<InvestorResponse> getAllInvestors() {
        return investorRepository.findAll()
                .stream()
                .map(investor -> new InvestorResponse(
                        investor.getId(),
                        investor.getName()
                ))
                .toList();
    }
}