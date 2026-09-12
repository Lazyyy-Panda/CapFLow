package com.aaditya.capFlow.controller;

import com.aaditya.capFlow.dto.CapitalCallResponse;
import com.aaditya.capFlow.dto.CreateCapitalCallRequest;
import com.aaditya.capFlow.service.CapitalCallService;
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
@RequestMapping("/api/commitments")
public class CapitalCallController {

    private final CapitalCallService capitalCallService;

    public CapitalCallController(CapitalCallService capitalCallService) {
        this.capitalCallService = capitalCallService;
    }

    @PostMapping("/{capitalCommitmentId}/calls")
    public ResponseEntity<CapitalCallResponse> createCapitalCall(
            @PathVariable Long capitalCommitmentId,
            @Valid @RequestBody CreateCapitalCallRequest request
    ) {
        CapitalCallResponse response = capitalCallService.createCapitalCall(
                capitalCommitmentId,
                request
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{capitalCommitmentId}/calls")
    public ResponseEntity<List<CapitalCallResponse>> getCallsByCommitmentId(
            @PathVariable Long capitalCommitmentId
    ) {
        return ResponseEntity.ok(
                capitalCallService.getCallsByCommitmentId(capitalCommitmentId)
        );
    }
}