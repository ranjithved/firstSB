package com.example.firstSB.repository;

import com.example.firstSB.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    public List<Employee> findByCity(String city);

    public List<Employee> findByName(String name);

    // public List<Employee> findByNameAndCity(String name, String city);
    // public List<Employee> findByNameOrCity(String name, String city);
}
