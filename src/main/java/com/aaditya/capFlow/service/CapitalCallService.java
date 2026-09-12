package com.aaditya.capFlow.service;

import com.aaditya.capFlow.dto.CapitalCallResponse;
import com.aaditya.capFlow.dto.CreateCapitalCallRequest;
import com.aaditya.capFlow.entity.CapitalCall;
import com.aaditya.capFlow.entity.CapitalCommitment;
import com.aaditya.capFlow.entity.PaymentStatus;
import com.aaditya.capFlow.exception.ResourceNotFoundException;
import com.aaditya.capFlow.repository.CapitalCallRepository;
import com.aaditya.capFlow.repository.CapitalCommitmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CapitalCallService {

    private final CapitalCommitmentRepository capitalCommitmentRepository;
    private final CapitalCallRepository capitalCallRepository;

    public CapitalCallService(
            CapitalCommitmentRepository capitalCommitmentRepository,
            CapitalCallRepository capitalCallRepository
    ) {
        this.capitalCommitmentRepository = capitalCommitmentRepository;
        this.capitalCallRepository = capitalCallRepository;
    }

    public CapitalCallResponse createCapitalCall(
            Long capitalCommitmentId,
            CreateCapitalCallRequest request
    ) {
        CapitalCommitment commitment = capitalCommitmentRepository
                .findById(capitalCommitmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Capital commitment not found with id " + capitalCommitmentId
                ));

        BigDecimal totalPreviouslyCalled = capitalCallRepository
                .findByCapitalCommitmentId(capitalCommitmentId)
                .stream()
                .map(CapitalCall::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal newTotalCalled = totalPreviouslyCalled.add(request.amount());

        if (newTotalCalled.compareTo(commitment.getAmount()) > 0) {
            throw new IllegalArgumentException("Capital call exceeds remaining commitment amount");
        }

        CapitalCall capitalCall = new CapitalCall(
                commitment,
                request.amount(),
                request.dueDate(),
                PaymentStatus.PENDING
        );

        CapitalCall savedCall = capitalCallRepository.save(capitalCall);

        return new CapitalCallResponse(
                savedCall.getId(),
                savedCall.getCapitalCommitment().getId(),
                savedCall.getAmount(),
                savedCall.getDueDate(),
                savedCall.getStatus()
        );
    }

    public List<CapitalCallResponse> getCallsByCommitmentId(Long capitalCommitmentId) {
        if (!capitalCommitmentRepository.existsById(capitalCommitmentId)) {
            throw new ResourceNotFoundException(
                    "Capital commitment not found with id " + capitalCommitmentId
            );
        }

        return capitalCallRepository.findByCapitalCommitmentId(capitalCommitmentId)
                .stream()
                .map(call -> new CapitalCallResponse(
                        call.getId(),
                        call.getCapitalCommitment().getId(),
                        call.getAmount(),
                        call.getDueDate(),
                        call.getStatus()
                ))
                .toList();
    }
}