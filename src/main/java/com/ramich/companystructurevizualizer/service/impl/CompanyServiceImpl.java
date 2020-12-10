package com.ramich.companystructurevizualizer.service.impl;

import com.ramich.companystructurevizualizer.model.Company;
import com.ramich.companystructurevizualizer.repository.CompanyRepository;
import com.ramich.companystructurevizualizer.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompanyById(int id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company updateCompany(int id, Company company) {
        Optional<Company> company1 = companyRepository.findById(id);
        if (!company1.isPresent()){
            throw new EntityNotFoundException("Company not found");
        }
        Company ccc = company1.get();
        ccc.setId(id);
        ccc.setName(company.getName());
        return companyRepository.save(ccc);
    }

    @Override
    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }
}
