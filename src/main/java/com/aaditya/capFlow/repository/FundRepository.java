package com.aaditya.capFlow.repository;

import com.aaditya.capFlow.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund, Long> {
}