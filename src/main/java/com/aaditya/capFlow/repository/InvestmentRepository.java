package com.aaditya.capFlow.repository;

import com.aaditya.capFlow.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    List<Investment> findByFundId(Long fundId);
}