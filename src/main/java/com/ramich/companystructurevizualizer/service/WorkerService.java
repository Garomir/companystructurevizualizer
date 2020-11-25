package com.ramich.companystructurevizualizer.service;

import com.ramich.companystructurevizualizer.model.Worker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface WorkerService {
    Worker addWorker(Worker worker);
    List<Worker> getAllWorkers();
    List<Worker> getAllWorkersByDepartment(int id);
    Optional<Worker> getWorkerById(int id);
    Worker updateWorker(int id, Worker worker);
    void deleteWorker(int id);
}
