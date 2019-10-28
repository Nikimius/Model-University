package com.example.demo.vuz.controllers;

import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.TeacherRepository;
import com.example.demo.vuz.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
    public List<Teacher> getListTeacher(){
        return teacherRepository.findAll();
    }

    @GetMapping("/teachers/{teacherId}")
    public Teacher getTeacher(@PathVariable(name = "teacherId") int teacherId){
        return inMemoryStorage.getTeacherById(teacherId);

    }

    @PostMapping("/teachers")
    @Transactional
    public void createTeacher(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        teacherService.createTeachers(firstName, lastName);
    }

    @PostMapping("/delTeachers")
    @Transactional
    public void delTeachers(@RequestParam("studentListIds") List<Integer> teachersIds){
        teacherService.removeTeachers(teachersIds);
    }

    @PostMapping("/changeDepartmentToTeacher")
    @Transactional
    public void changeTeachers(@RequestParam("teacherId") int teacherId, @RequestParam("departmentId") int departmentId){
        teacherService.changeDepartmentToTeacher(teacherId, departmentId);
    }


}
