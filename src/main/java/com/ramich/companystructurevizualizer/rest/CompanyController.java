package com.ramich.companystructurevizualizer.rest;

import com.ramich.companystructurevizualizer.model.Department;
import com.ramich.companystructurevizualizer.model.Worker;
import com.ramich.companystructurevizualizer.service.DepartmentService;
import com.ramich.companystructurevizualizer.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        Department dep = departmentService.addDepartment(department);
        return ResponseEntity.status(201).body(dep);
    }

    @PostMapping("/worker")
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker){
        Worker work = workerService.addWorker(worker);
        return ResponseEntity.status(201).body(work);
    }

    @GetMapping("/worker")
    public ResponseEntity<List<Worker>> gelAllWorkers(){
        List<Worker> workers = workerService.getAllWorkers();
        return ResponseEntity.ok().body(workers);
    }

    @GetMapping("/department")
    public ResponseEntity<List<Department>> gelAllDepartments(){
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok().body(departments);
    }

    @GetMapping("/department/worker/{id}")
    public ResponseEntity<List<Worker>> gelAllWorkersByDepartment(@PathVariable("id") int id){
        List<Worker> workers2 = workerService.getAllWorkersByDepartment(id);
        return ResponseEntity.ok().body(workers2);
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
        if (!department.isPresent()){
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
