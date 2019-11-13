package com.example.demo.vuz.repositories;

import com.example.demo.vuz.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupeRepository extends JpaRepository<Groups, Integer> {

    @Query(value = "SELECT g from Groups g left join fetch g.studentList")
    List<Groups> findAllByIdIn(List<Integer> ids);

}
