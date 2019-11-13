package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupeRepository groupeRepository;

    private int age = 10;

    public StudentService(StudentRepository studentRepository, GroupeRepository groupeRepository) {
        this.studentRepository = studentRepository;
        this.groupeRepository = groupeRepository;
    }

//  Для одного элемента
    /*public void removeStudent(Integer studentId){
        studentRepository.delete(studentId);
    }*/

    //  Для списка элементов
    public void removeStudent(List<Integer> studentsIds) {
        studentRepository.deleteAllByIdIn(studentsIds);
    }

    public void studentChangeGroup(List<Integer> studentsIds, int idGroup) {
        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach(student -> student.setGroup(groupeRepository.findById(idGroup).orElseThrow(() -> new IllegalArgumentException("Group not found"))));
        students.forEach(student -> studentRepository.save(student));
    }

    public Student createStudents(String fistName, String lastName){
        Student newStudent = new Student();
        newStudent.setFirstName(fistName);
        newStudent.setLastName(lastName);
        newStudent.setAge(Math.abs(new Random().nextInt() % 100));
        newStudent.setStudentNumber(Math.abs(new Random().nextInt() % 1000_000));

        return studentRepository.save(newStudent);

    }

    public void removeStudentsFromGroup(List<Integer> stIds){
        List<Student> students = studentRepository.findAllById(stIds);
        students.forEach(student -> student.setGroup(null));
        students.forEach(student -> studentRepository.save(student));
    }

}
