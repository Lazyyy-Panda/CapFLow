package com.aaditya.capFlow.repository;

import com.aaditya.capFlow.entity.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
}