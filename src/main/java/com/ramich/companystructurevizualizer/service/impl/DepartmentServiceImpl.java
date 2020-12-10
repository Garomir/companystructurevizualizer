package com.ramich.companystructurevizualizer.service.impl;

import com.ramich.companystructurevizualizer.model.Department;
import com.ramich.companystructurevizualizer.repository.DepartmentRepository;
import com.ramich.companystructurevizualizer.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartmentsByCompany(int id) {
        return departmentRepository.findByCompany(id);
    }

    @Override
    public Optional<Department> getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department updateDepartment(int id, Department department) {
        Optional<Department> department1 = departmentRepository.findById(id);
        if (!department1.isPresent()){
            throw new EntityNotFoundException("Entity not found");
        }
        Department ddd = department1.get();
        ddd.setId(id);
        ddd.setName(department.getName());
        return departmentRepository.save(ddd);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }
}
