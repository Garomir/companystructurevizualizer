package com.ramich.companystructurevizualizer.service;

import com.ramich.companystructurevizualizer.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department addDepartment(Department department);
    List<Department> getAllDepartmentsByCompany(int id);
    Optional<Department> getDepartmentById(int id);
    Department updateDepartment(int id, Department department);
    void deleteDepartment(int id);
}
