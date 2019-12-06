package com.example.demo.vuz.controllers;

import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.dto.TeacherDto;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.TeacherRepository;
import com.example.demo.vuz.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teachers")
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

    @GetMapping()
    public List<Teacher> getListTeacher() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{teacherId}")
    public Teacher getTeacher(@PathVariable(name = "teacherId") int teacherId) {
        return inMemoryStorage.getTeacherById(teacherId);
    }

    @PostMapping()
    public Teacher createTeacher(@RequestBody TeacherDto teacherDto) {
        return teacherService.createTeacher(teacherDto.getFirstName(), teacherDto.getLastName());
    }

    @PatchMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Map<String, Object> teacher) {
       return teacherService.updateTeacher(id, teacher);
    }

    @DeleteMapping()
    public void delTeachers(@RequestBody TeacherDto teacherDto) {
        teacherService.deleteTeachersByIdIn(teacherDto.getTeachersIds());
    }


}
