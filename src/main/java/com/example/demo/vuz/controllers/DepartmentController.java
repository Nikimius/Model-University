package com.example.demo.vuz.controllers;


import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Group;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import com.example.demo.vuz.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class DepartmentController {

    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final DepartmentRepository departmentRepository;
    private final GroupeRepository groupeRepository;
    private final TeacherRepository teacherRepository;
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DemoApplication.InMemoryStorage inMemoryStorage, DepartmentRepository departmentRepository, GroupeRepository groupeRepository, TeacherRepository teacherRepository, DepartmentService departmentService) {
        this.inMemoryStorage = inMemoryStorage;
        this.departmentRepository = departmentRepository;
        this.groupeRepository = groupeRepository;
        this.teacherRepository = teacherRepository;
        this.departmentService = departmentService;
    }


    @GetMapping("departments")
    public List<Department> getListDepartment() {
        return departmentRepository.findAll();
    }

    //    Смысла нет, так как просто выделяет из всего множества
    @GetMapping("departments/{departmentId}")
    public Department getDepartment(@PathVariable(name = "departmentId") int departmentId) {
        return inMemoryStorage.getDepartmentById(departmentId);
    }

    @GetMapping("departments/{departmentId}/groups")
    public List<Group> getGroup(@PathVariable(name = "departmentId") int departmentId) {
        return inMemoryStorage.getDepartmentById(departmentId).getGroupList();
    }

    @GetMapping("departments/{departmentId}/teachers")
    public List<Teacher> getTeacher(@PathVariable(name = "departmentId") int departmentId) {
        return inMemoryStorage.getDepartmentById(departmentId).getTeacherList();
    }

    @PostMapping("/departments")
    @Transactional
    public void createDepartments(@RequestParam("nameDepartment") String name,
                                  @RequestParam(value = "groupList", required = false) List<Integer> groupsIds,
                                  @RequestParam(value = "teacherList", required = false) List<Integer> teachersIds) {

        departmentService.createDep(name, groupsIds, teachersIds);
    }

    @PostMapping("/delDepartments")
    @Transactional
    public void createDepartments(@RequestParam("departmentListIds") List<Integer> departmentsIds) {

        departmentService.removeDepartment(departmentsIds);
    }
}
