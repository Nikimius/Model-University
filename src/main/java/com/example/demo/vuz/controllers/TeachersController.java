package com.example.demo.vuz.controllers;

import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.dto.TeacherDto;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.TeacherRepository;
import com.example.demo.vuz.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class TeachersController {

    private final TeacherRepository teacherRepository;
    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final TeacherService teacherService;

    @Autowired
    public TeachersController(TeacherRepository teacherRepository, DemoApplication.InMemoryStorage inMemoryStorage, TeacherService teacherService) {
        this.teacherRepository = teacherRepository;
        this.inMemoryStorage = inMemoryStorage;
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<Teacher> getListTeacher() {
        return teacherRepository.findAll();
    }

    @GetMapping("/teachers/{teacherId}")
    public Teacher getTeacher(@PathVariable(name = "teacherId") int teacherId) {
        return inMemoryStorage.getTeacherById(teacherId);

    }

    @PostMapping("/teachers")
    public void createTeacherV2(@RequestBody TeacherDto teacherDto) {
        teacherService.createTeachers(teacherDto.getFirstName(), teacherDto.getLastName());
    }

    /*@PostMapping("/teachers")
    public void createTeacher(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName){
        teacherService.createTeachers(firstName, lastName);
    }*/

    @DeleteMapping("/teachers/all")
    public void delTeachers(@RequestBody TeacherDto teacherDto) {
        teacherService.removeTeachers(teacherDto.getTeachersIds());
    }

    @DeleteMapping("/teachers/{teacherId}")
    public void deleteTeacher(@PathVariable(name = "teacherId") Integer teacherId) {
        teacherService.removeTeachers(Collections.singletonList(teacherId));
    }

    /*@PostMapping("/delTeachers")
    public void delTeachers(@RequestParam("studentListIds") List<Integer> teachersIds){
        teacherService.removeTeachers(teachersIds);
    }*/

    @PatchMapping("/departmentToTeacher")
    public void changeTeachersDepartments(@RequestBody TeacherDto teacherDto) {
        teacherService.changeDepartmentToTeacher(teacherDto.getTeachersIds(), teacherDto.getDepartment());
    }

    /*@PostMapping("/changeDepartmentToTeacher")
    public void changeTeachers(@RequestParam("teacherListIds") List<Integer> teachersIds,
                               @RequestParam("departmentId") int departmentId){
        teacherService.changeDepartmentToTeacher(teachersIds, departmentId);
    }*/

    @DeleteMapping("/teachersFromDep")
    public void deleteTeachersFromDepartment(@RequestBody TeacherDto teacherDto) {
        teacherService.removeTeacherFromGroup(teacherDto.getTeachersIds());
    }
}
