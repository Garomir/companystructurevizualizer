package com.ramich.companystructurevizualizer.rest;

import com.ramich.companystructurevizualizer.model.Company;
import com.ramich.companystructurevizualizer.model.Department;
import com.ramich.companystructurevizualizer.model.Worker;
import com.ramich.companystructurevizualizer.service.CompanyService;
import com.ramich.companystructurevizualizer.service.DepartmentService;
import com.ramich.companystructurevizualizer.service.WorkerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/structure")
@AllArgsConstructor
public class CompanyController {

    private final WorkerService workerService;

    private final DepartmentService departmentService;

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company){
        Company com = companyService.addCompany(company);
        return ResponseEntity.status(201).body(com);
    }

    @GetMapping
    public ResponseEntity<List<Company>> gelAllCompanies(){
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok().body(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") int id){
        Optional<Company> company = companyService.getCompanyById(id);
        if (!company.isPresent()){
            throw new EntityNotFoundException("Company not found " + id);
        }
        return ResponseEntity.ok().body(company.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable("id") int id, @RequestBody Company company){
        Company c = companyService.updateCompany(id, company);
        return ResponseEntity.ok().body(c);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") int id){
        companyService.deleteCompany(id);
    }

    @PostMapping("/{id}/department")
    public ResponseEntity<Department> addDepartment(@PathVariable("id") int id,
                                                    @RequestBody Department department){
        Optional<Company> company = companyService.getCompanyById(id);
        if (!company.isPresent()){
            throw new EntityNotFoundException("Company not found " + id);
        }
        department.setCompany(company.get());
        departmentService.addDepartment(department);
        return ResponseEntity.status(201).body(department);
    }

    @PostMapping("/{comId}/department/{depId}/worker")
    public ResponseEntity<Worker> addWorker(@PathVariable("depId") int depId,
                                            @PathVariable("comId") int comId,
                                            @RequestBody Worker worker){
        Optional<Company> company = companyService.getCompanyById(comId);
        if (!company.isPresent()){
            throw new EntityNotFoundException("Company not found " + comId);
        }
        Optional<Department> department = departmentService.getDepartmentById(depId);
        if (!department.isPresent()){
            throw new EntityNotFoundException("Department not found " + depId);
        }
        worker.setDepartment(department.get());
        worker.setCompany(company.get());
        workerService.addWorker(worker);
        return ResponseEntity.status(201).body(worker);
    }

    @GetMapping("/worker")
    public ResponseEntity<List<Worker>> gelAllWorkers(){
        List<Worker> workers = workerService.getAllWorkers();
        return ResponseEntity.ok().body(workers);
    }

    @GetMapping("/company/{id}/department")
    public ResponseEntity<List<Department>> gelAllDepartmentsByCompany(@PathVariable("id") int id){
        List<Department> departments = departmentService.getAllDepartmentsByCompany(id);
        return ResponseEntity.ok().body(departments);
    }

    @GetMapping("/department/worker/{id}")
    public ResponseEntity<List<Worker>> gelAllWorkersByDepartment(@PathVariable("id") int id){
        List<Worker> workers2 = workerService.getAllWorkersByDepartment(id);
        return ResponseEntity.ok().body(workers2);
    }

    @GetMapping("/company/worker/{id}")
    public ResponseEntity<List<Worker>> gelAllWorkersByCompany(@PathVariable("id") int id){
        List<Worker> workers = workerService.getAllWorkersByCompany(id);
        return ResponseEntity.ok().body(workers);
    }

    @GetMapping("/worker/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable("id") int id){
        Optional<Worker> worker = workerService.getWorkerById(id);
        if (!worker.isPresent()){
            throw new EntityNotFoundException("Worker not found " + id);
        }

        return ResponseEntity.ok().body(worker.get());
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentyId(@PathVariable("id") int id){
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (department.isEmpty()){
            throw new EntityNotFoundException("Department not found " + id);
        }
        return ResponseEntity.ok().body(department.get());
    }

    @PutMapping("/worker/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable("id") int id, @RequestBody Worker worker){
        Worker w = workerService.updateWorker(id, worker);
        return ResponseEntity.ok().body(w);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") int id, @RequestBody Department department){
        Department d = departmentService.updateDepartment(id, department);
        return ResponseEntity.ok().body(d);
    }

    @DeleteMapping("/worker/{id}")
    public void deleteWorker(@PathVariable("id") int id){
        workerService.deleteWorker(id);
    }

    @DeleteMapping("/department/{id}")
    public void deleteDepartment(@PathVariable("id") int id){
        departmentService.deleteDepartment(id);
    }
}
