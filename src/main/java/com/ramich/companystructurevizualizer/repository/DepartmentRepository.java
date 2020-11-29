package com.ramich.companystructurevizualizer.repository;

import com.ramich.companystructurevizualizer.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query(value = "select * from department where company_id = :companyId",nativeQuery = true)
    List<Department> findByCompany(@Param("companyId") int companyId);
}
