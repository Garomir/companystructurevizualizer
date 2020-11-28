package com.ramich.companystructurevizualizer.service.impl;

import com.ramich.companystructurevizualizer.model.Worker;
import com.ramich.companystructurevizualizer.repository.WorkerRepository;
import com.ramich.companystructurevizualizer.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public Worker addWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public List<Worker> getAllWorkersByDepartment(int id) {
        return workerRepository.findByDepartment(id);
    }

    @Override
    public Optional<Worker> getWorkerById(int id) {
        return workerRepository.findById(id);
    }

    @Override
    public Worker updateWorker(int id, Worker worker) {
        Optional<Worker> worker1 = workerRepository.findById(id);
        if (!worker1.isPresent()){
            throw new EntityNotFoundException("Entity not found");
        }
        Worker www = worker1.get();
        www.setId(id);
        www.setFirstname(worker.getFirstname());
        www.setLastname(worker.getLastname());
        www.setPosition(worker.getPosition());
        return workerRepository.save(www);
    }

    @Override
    public void deleteWorker(int id) {
        workerRepository.deleteById(id);
    }
}
