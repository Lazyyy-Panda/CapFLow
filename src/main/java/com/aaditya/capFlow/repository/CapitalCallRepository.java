package com.aaditya.capFlow.repository;

import com.aaditya.capFlow.entity.CapitalCall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CapitalCallRepository extends JpaRepository<CapitalCall, Long> {

    List<CapitalCall> findByCapitalCommitmentId(Long capitalCommitmentId);
}