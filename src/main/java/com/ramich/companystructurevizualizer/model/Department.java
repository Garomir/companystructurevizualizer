package com.ramich.companystructurevizualizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;

    public Department() {
    }

    public Department(int id, String name, Set<Worker> workers) {
        this.id = id;
        this.name = name;
        this.workers = workers;
    }

    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Worker> workers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
        for (Worker worker: workers) {
            worker.setDepartment(this);
        }
    }
}
