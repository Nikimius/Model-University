package com.example.demo.vuz.repositories;

import com.example.demo.vuz.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    void deleteAllByIdIn(List<Integer> list);
}
