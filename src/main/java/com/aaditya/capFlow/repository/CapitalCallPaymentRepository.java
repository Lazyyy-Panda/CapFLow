package com.aaditya.capFlow.repository;

import com.aaditya.capFlow.entity.CapitalCallPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CapitalCallPaymentRepository
        extends JpaRepository<CapitalCallPayment, Long> {

    List<CapitalCallPayment> findByCapitalCallId(Long capitalCallId);
}