package com.example.demo.vuz.services;

import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

private final StudentRepository studentRepository;
private final GroupeRepository groupeRepository;


    public StudentService(StudentRepository studentRepository, GroupeRepository groupeRepository) {
        this.studentRepository = studentRepository;
        this.groupeRepository = groupeRepository;
    }


    public void removeStudent(Integer idStudent){
        if(studentRepository.existsById(idStudent)){
            studentRepository.deleteById(idStudent);
        }
    }

    public void studentChangeGroup(int idStudent, int idGroup){
        studentRepository.findById(idStudent).orElseThrow(()-> new IllegalArgumentException("Student not found"))
                .setGroup(groupeRepository.findById(idGroup).orElseThrow(()-> new IllegalArgumentException("Group not found")));
    }


}
