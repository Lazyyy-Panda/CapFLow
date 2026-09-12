package com.aaditya.capFlow.repository;

import com.aaditya.capFlow.entity.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistributionRepository
        extends JpaRepository<Distribution, Long> {

    List<Distribution> findByFundId(Long fundId);
}