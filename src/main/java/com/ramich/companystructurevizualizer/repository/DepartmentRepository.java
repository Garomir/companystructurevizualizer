package com.ramich.companystructurevizualizer.repository;

import com.ramich.companystructurevizualizer.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
