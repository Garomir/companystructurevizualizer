package com.ramich.companystructurevizualizer.service.impl;

import com.ramich.companystructurevizualizer.model.Department;
import com.ramich.companystructurevizualizer.repository.DepartmentRepository;
import com.ramich.companystructurevizualizer.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
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
