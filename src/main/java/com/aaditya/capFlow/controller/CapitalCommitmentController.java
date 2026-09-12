package com.aaditya.capFlow.controller;

import com.aaditya.capFlow.dto.CapitalCommitmentResponse;
import com.aaditya.capFlow.dto.CreateCapitalCommitmentRequest;
import com.aaditya.capFlow.service.CapitalCommitmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/funds")
public class CapitalCommitmentController {

    private final CapitalCommitmentService capitalCommitmentService;

    public CapitalCommitmentController(
            CapitalCommitmentService capitalCommitmentService
    ) {
        this.capitalCommitmentService = capitalCommitmentService;
    }

    @PostMapping("/{fundId}/commitments")
    public ResponseEntity<CapitalCommitmentResponse> createCommitment(
            @PathVariable Long fundId,
            @Valid @RequestBody CreateCapitalCommitmentRequest request
    ) {
        CapitalCommitmentResponse response =
                capitalCommitmentService.createCommitment(fundId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{fundId}/commitments")
    public ResponseEntity<List<CapitalCommitmentResponse>> getCommitmentsByFundId(
            @PathVariable Long fundId
    ) {
        return ResponseEntity.ok(
                capitalCommitmentService.getCommitmentsByFundId(fundId)
        );
    }
}