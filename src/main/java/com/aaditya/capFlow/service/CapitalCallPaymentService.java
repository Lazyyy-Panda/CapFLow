package com.aaditya.capFlow.service;

import com.aaditya.capFlow.dto.CapitalCallPaymentResponse;
import com.aaditya.capFlow.dto.CreateCapitalCallPaymentRequest;
import com.aaditya.capFlow.entity.CapitalCall;
import com.aaditya.capFlow.entity.CapitalCallPayment;
import com.aaditya.capFlow.entity.PaymentStatus;
import com.aaditya.capFlow.exception.ResourceNotFoundException;
import com.aaditya.capFlow.repository.CapitalCallPaymentRepository;
import com.aaditya.capFlow.repository.CapitalCallRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CapitalCallPaymentService {

    private final CapitalCallRepository capitalCallRepository;
    private final CapitalCallPaymentRepository capitalCallPaymentRepository;

    public CapitalCallPaymentService(
            CapitalCallRepository capitalCallRepository,
            CapitalCallPaymentRepository capitalCallPaymentRepository
    ) {
        this.capitalCallRepository = capitalCallRepository;
        this.capitalCallPaymentRepository = capitalCallPaymentRepository;
    }

    public CapitalCallPaymentResponse recordPayment(
            Long capitalCallId,
            CreateCapitalCallPaymentRequest request
    ) {
        CapitalCall capitalCall = capitalCallRepository.findById(capitalCallId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Capital call not found with id " + capitalCallId
                ));

        CapitalCallPayment payment = new CapitalCallPayment(
                capitalCall,
                request.amount(),
                request.paymentDate()
        );

        CapitalCallPayment savedPayment = capitalCallPaymentRepository.save(payment);

        BigDecimal totalPaid = capitalCallPaymentRepository
                .findByCapitalCallId(capitalCallId)
                .stream()
                .map(CapitalCallPayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int comparison = totalPaid.compareTo(capitalCall.getAmount());

        if (comparison < 0) {
            capitalCall.setStatus(PaymentStatus.PARTIALLY_PAID);
        } else if (comparison == 0) {
            capitalCall.setStatus(PaymentStatus.PAID);
        } else {
            capitalCall.setStatus(PaymentStatus.OVERPAID);
        }

        capitalCallRepository.save(capitalCall);

        return new CapitalCallPaymentResponse(
                savedPayment.getId(),
                savedPayment.getCapitalCall().getId(),
                savedPayment.getAmount(),
                savedPayment.getPaymentDate()
        );
    }

    public List<CapitalCallPaymentResponse> getPaymentsByCapitalCallId(Long capitalCallId) {
        if (!capitalCallRepository.existsById(capitalCallId)) {
            throw new ResourceNotFoundException(
                    "Capital call not found with id " + capitalCallId
            );
        }

        return capitalCallPaymentRepository.findByCapitalCallId(capitalCallId)
                .stream()
                .map(payment -> new CapitalCallPaymentResponse(
                        payment.getId(),
                        payment.getCapitalCall().getId(),
                        payment.getAmount(),
                        payment.getPaymentDate()
                ))
                .toList();
    }
}