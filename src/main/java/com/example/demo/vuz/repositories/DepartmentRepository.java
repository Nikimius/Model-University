package com.example.demo.vuz.repositories;

import com.example.demo.vuz.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
