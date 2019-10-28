package com.example.demo.vuz.controllers;


import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.services.StudentService;
import com.example.demo.vuz.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class StudentController {

    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Autowired
    public StudentController(DemoApplication.InMemoryStorage inMemoryStorage,
                             StudentRepository studentRepository, StudentService studentService){
        this.inMemoryStorage = inMemoryStorage;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }


    @GetMapping("/students")
    public List<Student> getListGroup(){
        return studentRepository.findAll();
        //return inMemoryStorage.studentList;
    }
    @GetMapping("/students/{studentId}")
    public Student getGroup(@PathVariable (name = "studentId") int studentId){
        return inMemoryStorage.getStudentById(studentId);
    }

    @PostMapping("/students")
    public void createStudent(@RequestParam("fn") String fn, @RequestParam("ln") String ln) {
        Student newStudent = new Student();
        newStudent.setFirstName(fn);
        newStudent.setLastName(ln);
        newStudent.setAge(Math.abs(new Random().nextInt() % 100));
        newStudent.setStudentNumber(Math.abs(new Random().nextInt() % 1000000));

        studentRepository.save(newStudent);
    }

    @PostMapping("/studentChangeGroup")
    @Transactional
    public void studentChange(@RequestParam("studentId") int studentId,
                                   @RequestParam("groupId") int groupId){
        studentService.studentChangeGroup(studentId, groupId);
    }

    @PostMapping("/removeStudent")
    @Transactional
    public void removeStudent(@RequestParam("studentListId") List<Integer> studentsIds){
        studentService.removeStudent(studentsIds);
    }
}
