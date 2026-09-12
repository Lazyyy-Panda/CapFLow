package com.aaditya.capFlow.repository;

import com.aaditya.capFlow.entity.CapitalCommitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CapitalCommitmentRepository
        extends JpaRepository<CapitalCommitment, Long> {

    List<CapitalCommitment> findByFundId(Long fundId);
}