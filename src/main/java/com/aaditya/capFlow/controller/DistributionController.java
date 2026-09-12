package com.aaditya.capFlow.controller;

import com.aaditya.capFlow.dto.CreateDistributionRequest;
import com.aaditya.capFlow.dto.DistributionResponse;
import com.aaditya.capFlow.service.DistributionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/funds/{fundId}/distributions")
public class DistributionController {

    private final DistributionService distributionService;

    public DistributionController(DistributionService distributionService) {
        this.distributionService = distributionService;
    }

    @PostMapping
    public ResponseEntity<DistributionResponse> createDistribution(
            @PathVariable Long fundId,
            @Valid @RequestBody CreateDistributionRequest request
    ) {
        DistributionResponse response =
                distributionService.createDistribution(fundId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<DistributionResponse>> getDistributionsByFundId(
            @PathVariable Long fundId
    ) {
        return ResponseEntity.ok(
                distributionService.getDistributionsByFundId(fundId)
        );
    }
}