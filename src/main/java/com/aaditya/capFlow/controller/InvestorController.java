package com.aaditya.capFlow.controller;

import com.aaditya.capFlow.dto.CreateInvestorRequest;
import com.aaditya.capFlow.dto.InvestorResponse;
import com.aaditya.capFlow.service.InvestorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/investors")
public class InvestorController {

    private final InvestorService investorService;

    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @PostMapping
    public ResponseEntity<InvestorResponse> createInvestor(
            @Valid @RequestBody CreateInvestorRequest request
    ) {
        InvestorResponse response = investorService.createInvestor(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<InvestorResponse>> getAllInvestors() {
        return ResponseEntity.ok(investorService.getAllInvestors());
    }
}