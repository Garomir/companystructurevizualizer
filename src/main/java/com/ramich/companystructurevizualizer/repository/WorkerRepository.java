package com.ramich.companystructurevizualizer.repository;

import com.ramich.companystructurevizualizer.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    @Query(value = "select * from worker where department_id = :departmentId",nativeQuery = true)
    List<Worker> findByDepartment(@Param("departmentId") int departmentId);

    @Query(value = "select * from worker where company_id = :companyId",nativeQuery = true)
    List<Worker> findByCompany(@Param("companyId") int companyId);
}
