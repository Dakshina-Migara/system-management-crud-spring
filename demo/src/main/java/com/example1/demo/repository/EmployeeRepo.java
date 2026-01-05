package com.example1.demo.repository;

import com.example1.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByNic(String nic);
}
//hibernate eke thiyena method tika gannawa