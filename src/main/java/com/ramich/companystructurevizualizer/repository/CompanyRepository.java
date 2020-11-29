package com.ramich.companystructurevizualizer.repository;

import com.ramich.companystructurevizualizer.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
