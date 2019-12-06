package com.example.demo.vuz.repositories;

import com.example.demo.vuz.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    void deleteAllByIdIn(List<Integer> list);
}
