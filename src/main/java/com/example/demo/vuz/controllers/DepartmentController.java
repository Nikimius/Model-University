package com.example.demo.vuz.controllers;


import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Group;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class DepartmentController {

    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final DepartmentRepository departmentRepository;
    private final GroupeRepository groupeRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public DepartmentController(DemoApplication.InMemoryStorage inMemoryStorage, DepartmentRepository departmentRepository, GroupeRepository groupeRepository, TeacherRepository teacherRepository) {
        this.inMemoryStorage = inMemoryStorage;
        this.departmentRepository = departmentRepository;
        this.groupeRepository = groupeRepository;
        this.teacherRepository = teacherRepository;
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
    public List<Teacher> getTeacher (@PathVariable(name = "departmentId") int departmentId) {
        return inMemoryStorage.getDepartmentById(departmentId).getTeacherList();
    }

    @PostMapping("/departments")
    public void createDepartments(@RequestParam("nameDepartment") String name,
                                  @RequestParam("groupList") List<Integer> groupsIds,
                                  @RequestParam("teacherList") List<Integer> teachersIds){

        Department newDepartment = new Department();
        newDepartment.setName(name);

        newDepartment.setNumberTelephone(Math.abs(new Random().nextInt()%10000000));

        List<Group> groups = groupeRepository.findAllById(groupsIds);
        groups.forEach(group -> group.setDepartment(newDepartment));
        newDepartment.setGroupList(groups);

        List<Teacher> teachers = teacherRepository.findAllById(teachersIds);
        teachers.forEach(teacher -> teacher.setDepartment(newDepartment));
        newDepartment.setTeacherList(teachers);

        departmentRepository.save(newDepartment);
    }
}
