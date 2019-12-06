package com.example.demo.vuz.controllers;


import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.dto.StudentDto;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.StudentRepository;
import com.example.demo.vuz.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
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

    @DeleteMapping("/StudentsFromGroup")
    public void deleteStudentsFromGroup(@RequestBody StudentDto studentDto) {
        studentService.deleteStudentsFromGroup(studentDto.getStudentsIds());
    }

    /*@PostMapping("/removeStudent")
    @Transactional
    public void removeStudent(@RequestParam("studentListId") List<Integer> studentsIds) {
        studentService.removeStudent(studentsIds);
    }*/

    @PatchMapping("/{id}")
    public Student updateStudentById(@PathVariable int id, @RequestBody Map<String, String> objStudent){
        return studentService.updateStudentById(id, objStudent);
    }

    @DeleteMapping()
    public void deleteStudentsById(@RequestBody StudentDto studentDto) {
        studentService.deleteStudentsById(studentDto.getStudentsIds());
    }

    @PostMapping()
    public Student createStudentV2(@RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto.getFn(), studentDto.getLn());
    }


    /*@PostMapping("/students")
    @Transactional
    public void createStudent(@RequestParam("fn") String fn, @RequestParam("ln") String ln) {
        studentService.createStudents(fn, ln);
    }*/

    @GetMapping()
    public List<Student> getListGroup() {
        return studentRepository.findAll();
        //return inMemoryStorage.studentList;
    }

    @GetMapping("/{studentId}")
    public Student getGroup(@PathVariable(name = "studentId") int studentId) {
        return inMemoryStorage.getStudentById(studentId);
    }

}
