package com.example.demo.vuz.repositories;

import com.example.demo.vuz.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    //void deleteByIdIn(List<Integer> ids);

    void deleteById(Integer id);
}
