package com.aaditya.capFlow.service;

import com.aaditya.capFlow.dto.CapitalCallResponse;
import com.aaditya.capFlow.dto.CreateCapitalCallRequest;
import com.aaditya.capFlow.entity.CapitalCall;
import com.aaditya.capFlow.entity.CapitalCommitment;
import com.aaditya.capFlow.entity.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.aaditya.capFlow.repository.CapitalCallRepository;
import com.aaditya.capFlow.repository.CapitalCommitmentRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CapitalCallServiceTest {

    @Mock
    private CapitalCommitmentRepository capitalCommitmentRepository;

    @Mock
    private CapitalCallRepository capitalCallRepository;

    @InjectMocks
    private CapitalCallService capitalCallService;

    @Test
    void createCapitalCall_throwsException_whenCallExceedsCommitment() {
        CapitalCommitment commitment = mock(CapitalCommitment.class);

        when(commitment.getAmount())
                .thenReturn(new BigDecimal("1000000.00"));

        when(capitalCommitmentRepository.findById(1L))
                .thenReturn(Optional.of(commitment));

        CapitalCall existingCall = mock(CapitalCall.class);

        when(existingCall.getAmount())
                .thenReturn(new BigDecimal("900000.00"));

        when(capitalCallRepository.findByCapitalCommitmentId(1L))
                .thenReturn(List.of(existingCall));

        CreateCapitalCallRequest request =
                new CreateCapitalCallRequest(
                        new BigDecimal("200000.00"),
                        LocalDate.of(2026, 9, 14)
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> capitalCallService.createCapitalCall(1L, request)
        );

        verify(capitalCallRepository, never()).save(any(CapitalCall.class));
    }
    @Test
    void createCapitalCall_savesCall_whenTotalEqualsCommitment() {
        CapitalCommitment commitment = mock(CapitalCommitment.class);

        when(commitment.getId())
                .thenReturn(1L);

        when(commitment.getAmount())
                .thenReturn(new BigDecimal("1000000.00"));

        when(capitalCommitmentRepository.findById(1L))
                .thenReturn(Optional.of(commitment));

        CapitalCall existingCall = mock(CapitalCall.class);

        when(existingCall.getAmount())
                .thenReturn(new BigDecimal("900000.00"));

        when(capitalCallRepository.findByCapitalCommitmentId(1L))
                .thenReturn(List.of(existingCall));

        CreateCapitalCallRequest request =
                new CreateCapitalCallRequest(
                        new BigDecimal("100000.00"),
                        LocalDate.of(2026, 9, 14)
                );

        CapitalCall savedCall = mock(CapitalCall.class);

        when(savedCall.getId())
                .thenReturn(2L);

        when(savedCall.getCapitalCommitment())
                .thenReturn(commitment);

        when(savedCall.getAmount())
                .thenReturn(new BigDecimal("100000.00"));

        when(savedCall.getDueDate())
                .thenReturn(LocalDate.of(2026, 9, 14));

        when(savedCall.getStatus())
                .thenReturn(PaymentStatus.PENDING);

        when(capitalCallRepository.save(any(CapitalCall.class)))
                .thenReturn(savedCall);

        CapitalCallResponse response =
                capitalCallService.createCapitalCall(1L, request);

        assertEquals(2L, response.id());

        assertEquals(new BigDecimal("100000.00"), response.amount());

        assertEquals(PaymentStatus.PENDING, response.status());

        ArgumentCaptor<CapitalCall> capitalCallCaptor =
                ArgumentCaptor.forClass(CapitalCall.class);

        verify(capitalCallRepository, times(1))
                .save(capitalCallCaptor.capture());

        CapitalCall capturedCall = capitalCallCaptor.getValue();

        assertEquals(
                new BigDecimal("100000.00"),
                capturedCall.getAmount()
        );

        assertEquals(
                LocalDate.of(2026, 9, 14),
                capturedCall.getDueDate()
        );

        assertSame(
                commitment,
                capturedCall.getCapitalCommitment()
        );

        assertEquals(
                PaymentStatus.PENDING,
                capturedCall.getStatus()
        );
    }
}