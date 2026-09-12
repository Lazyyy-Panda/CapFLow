package com.aaditya.capFlow.controller;

import com.aaditya.capFlow.dto.CreateInvestmentRequest;
import com.aaditya.capFlow.dto.InvestmentResponse;
import com.aaditya.capFlow.service.InvestmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/funds")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping("/{fundId}/investments")
    public ResponseEntity<InvestmentResponse> createInvestment(
            @PathVariable Long fundId,
            @Valid @RequestBody CreateInvestmentRequest request
    ) {
        InvestmentResponse response = investmentService.createInvestment(fundId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{fundId}/investments")
    public ResponseEntity<List<InvestmentResponse>> getInvestmentsByFundId(
            @PathVariable Long fundId
    ) {
        List<InvestmentResponse> response =
                investmentService.getInvestmentsByFundId(fundId);

        return ResponseEntity.ok(response);
    }
}