package com.aaditya.capFlow.controller;

import com.aaditya.capFlow.dto.CreateFundRequest;
import com.aaditya.capFlow.dto.FundResponse;
import com.aaditya.capFlow.service.FundService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/funds")
public class FundController {

    private final FundService fundService;

    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @PostMapping
    public ResponseEntity<FundResponse> createFund(
            @Valid @RequestBody CreateFundRequest request
    ) {
        FundResponse response = fundService.createFund(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<FundResponse>> getAllFunds() {
        List<FundResponse> funds = fundService.getAllFunds();

        return ResponseEntity.ok(funds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FundResponse> getFundById(
            @PathVariable Long id
    ) {
        FundResponse fund = fundService.getFundById(id);

        return ResponseEntity.ok(fund);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FundResponse> updateFund(
            @PathVariable Long id,
            @Valid @RequestBody CreateFundRequest request
    ) {
        FundResponse response = fundService.updateFund(id, request);

        return ResponseEntity.ok(response);
    }
}