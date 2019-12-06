package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final GroupeRepository groupeRepository;
    private final int MAX_SIZE = 10;
    private int count = 0;

    public StudentService(StudentRepository studentRepository, GroupeRepository groupeRepository) {
        this.studentRepository = studentRepository;
        this.groupeRepository = groupeRepository;
    }

    public void deleteStudentsById(List<Integer> studentsIds) {
        studentRepository.deleteAllByIdIn(studentsIds);
    }

    public Student createStudent(String fistName, String lastName) {
        Student newStudent = new Student();
        newStudent.setFirstName(fistName);
        newStudent.setLastName(lastName);
        newStudent.setAge(Math.abs(new Random().nextInt() % 100));
        newStudent.setStudentNumber(Math.abs(new Random().nextInt() % 1000_000));

        return studentRepository.save(newStudent);
    }

    public Student updateStudentById(int studentId, Map<String, String> dto) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));

        if (dto.containsKey("firstName")) {
            student.setFirstName(dto.get("firstName"));
        }

        if (dto.containsKey("lastName")) {
            student.setLastName(dto.get("lastName"));
        }

        if(dto.containsKey("age")) {
            student.setAge(Integer.parseInt(dto.get("age")));
        }

        if(dto.containsKey("studentNumber")) {
            student.setStudentNumber(Integer.parseInt(dto.get("studentNumber")));
        }

        return studentRepository.save(student);
    }

    public void deleteStudentsFromGroup(List<Integer> stIds) {
        List<Student> students = studentRepository.findAllById(stIds);
        students.forEach(student -> student.setGroup(null));
        students.forEach(studentRepository::save);
    }
}