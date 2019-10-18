package com.example.demo.vuz.repositories;

import com.example.demo.vuz.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {


}
