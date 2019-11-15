package com.example.demo.vuz.controllers;


import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.dto.StudentDto;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.services.StudentService;
import com.example.demo.vuz.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Autowired
    public StudentController(DemoApplication.InMemoryStorage inMemoryStorage,
                             StudentRepository studentRepository, StudentService studentService) {
        this.inMemoryStorage = inMemoryStorage;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @PatchMapping("/studentChangeGroup")
    public void studentChangeGroup(@RequestBody StudentDto studentDto) {
        studentService.studentChangeGroup(studentDto.getStudentsIds(), studentDto.getGroupId());
    }

    /*@PostMapping("/delStudentsFromGroup")
        @Transactional
        public void delStudents(@RequestParam("studentListIds") List<Integer> studentsIds) {
            studentService.removeStudentsFromGroup(studentsIds);
     }*/

    @DeleteMapping("/StudentsFromGroup")
    public void delStudentsV2(@RequestBody StudentDto studentDto) {
        studentService.removeStudentsFromGroup(studentDto.getStudentsIds());
    }


    /*@PostMapping("/removeStudent")
    @Transactional
    public void removeStudent(@RequestParam("studentListId") List<Integer> studentsIds) {
        studentService.removeStudent(studentsIds);
    }*/

    @DeleteMapping("/students")
    public void removeStudentV2(@RequestBody StudentDto studentDto) {
        studentService.removeStudent(studentDto.getStudentsIds());
    }



    /*@PostMapping("/students")
    @Transactional
    public void createStudent(@RequestParam("fn") String fn, @RequestParam("ln") String ln) {
        studentService.createStudents(fn, ln);
    }*/

    @PostMapping("/students")
    public void createStudentV2(@RequestBody StudentDto studentDto) {
        studentService.createStudents(studentDto.getFn(), studentDto.getLn());
    }


    @GetMapping("/students")
    public List<Student> getListGroup() {
        return studentRepository.findAll();
        //return inMemoryStorage.studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student getGroup(@PathVariable(name = "studentId") int studentId) {
        return inMemoryStorage.getStudentById(studentId);
    }

}
