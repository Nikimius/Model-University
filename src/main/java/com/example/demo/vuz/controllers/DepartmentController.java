package com.example.demo.vuz.controllers;


import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.dto.DepartmentDto;
import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import com.example.demo.vuz.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
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

    @PostMapping()
    public Department createDepartments(@RequestBody DepartmentDto departmentDto) {
        return departmentService.createDepartment(departmentDto.getName(), departmentDto.getGroupsIds(),
                departmentDto.getTeachersIds());
    }

    @DeleteMapping()
    public void delDepartments(@RequestBody DepartmentDto departmentDto) {
        departmentService.deleteDepartmentsByIdIn(departmentDto.getDepartmentsIds());
    }

    @PatchMapping("/{id}")
    public Department addGroupInDepartment(@PathVariable int id, @RequestBody DepartmentDto departmentDto) {
        return departmentService.addGroupInDepartment(departmentDto.getGroupsIds(), id);
    }

    @GetMapping()
    public List<Department> getListDepartment() {
        return departmentRepository.findAll();
    }

    //    Смысла нет, так как просто выделяет из всего множества
    @GetMapping("/{departmentId}")
    public Department getDepartment(@PathVariable(name = "departmentId") int departmentId) {
        return inMemoryStorage.getDepartmentById(departmentId);
    }

    @GetMapping("/{departmentId}/groups")
    public List<Groups> getGroup(@PathVariable(name = "departmentId") int departmentId) {
        return inMemoryStorage.getDepartmentById(departmentId).getGroupList();
    }

    @GetMapping("/{departmentId}/teachers")
    public List<Teacher> getTeacher(@PathVariable(name = "departmentId") int departmentId) {
        return inMemoryStorage.getDepartmentById(departmentId).getTeacherList();
    }
}
