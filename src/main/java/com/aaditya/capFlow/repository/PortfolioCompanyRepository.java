package com.aaditya.capFlow.repository;

import com.aaditya.capFlow.entity.PortfolioCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aaditya.capFlow.entity.Fund;
import java.util.List;

public interface PortfolioCompanyRepository
        extends JpaRepository<PortfolioCompany, Long> {

    List<PortfolioCompany> findByFund(Fund fund);
}