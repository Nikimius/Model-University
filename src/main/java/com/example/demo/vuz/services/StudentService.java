package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

private final StudentRepository studentRepository;
private final GroupeRepository groupeRepository;


    public StudentService(StudentRepository studentRepository, GroupeRepository groupeRepository) {
        this.studentRepository = studentRepository;
        this.groupeRepository = groupeRepository;
    }
//  Для одного элемента
    /*public void removeStudent(Integer studentId){
        studentRepository.delete(studentId);
    }*/

//  Для списка элементов
    public void removeStudent(List<Integer> studentsIds){
        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach(student -> studentRepository.delete(student));
    }

    public void studentChangeGroup(int idStudent, int idGroup){
        studentRepository.findById(idStudent).orElseThrow(()-> new IllegalArgumentException("Student not found"))
                .setGroup(groupeRepository.findById(idGroup).orElseThrow(()-> new IllegalArgumentException("Group not found")));
        studentRepository.save(studentRepository.findById(idStudent).get());
    }


}
