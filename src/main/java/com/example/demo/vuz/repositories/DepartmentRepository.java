package com.example.demo.vuz.repositories;

import com.example.demo.vuz.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    //2-ой вариант, с помощью обычно  join, три селекта
    /*@Query(value = "select department from Department department left join fetch Teacher teacher " +
            "on department.id = teacher.departmentId " +
            "left join fetch Groups gr on department.id = gr.departmentId ")*/


    //3-ий вариант, нативный запрос, три селекта
    @Query(value = "select * from department department left join teacher teacher on department.id = teacher.department_id where department.id = 1", nativeQuery = true)

    // Тоже три селекта ;-(
    // @EntityGraph(value = "AnyName.name")


    //@Query(value = "select d from Department d left join fetch d.groupList")
    Department findAllByIdIn(List<Integer> ids);
}
//left join groups groupList on department.id = groupList.department_id