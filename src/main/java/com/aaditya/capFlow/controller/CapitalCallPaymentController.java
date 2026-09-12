package com.aaditya.capFlow.controller;

import com.aaditya.capFlow.dto.CapitalCallPaymentResponse;
import com.aaditya.capFlow.dto.CreateCapitalCallPaymentRequest;
import com.aaditya.capFlow.service.CapitalCallPaymentService;
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
@RequestMapping("/api/calls")
public class CapitalCallPaymentController {

    private final CapitalCallPaymentService capitalCallPaymentService;

    public CapitalCallPaymentController(
            CapitalCallPaymentService capitalCallPaymentService
    ) {
        this.capitalCallPaymentService = capitalCallPaymentService;
    }

    @PostMapping("/{capitalCallId}/payments")
    public ResponseEntity<CapitalCallPaymentResponse> recordPayment(
            @PathVariable Long capitalCallId,
            @Valid @RequestBody CreateCapitalCallPaymentRequest request
    ) {
        CapitalCallPaymentResponse response =
                capitalCallPaymentService.recordPayment(capitalCallId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{capitalCallId}/payments")
    public ResponseEntity<List<CapitalCallPaymentResponse>> getPaymentsByCapitalCallId(
            @PathVariable Long capitalCallId
    ) {
        return ResponseEntity.ok(
                capitalCallPaymentService.getPaymentsByCapitalCallId(capitalCallId)
        );
    }
}