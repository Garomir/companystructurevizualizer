package com.ramich.companystructurevizualizer.service;

import com.ramich.companystructurevizualizer.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company addCompany(Company company);
    List<Company> getAllCompanies();
    Optional<Company> getCompanyById(int id);
    Company updateCompany(int id, Company company);
    void deleteCompany(int id);
}
