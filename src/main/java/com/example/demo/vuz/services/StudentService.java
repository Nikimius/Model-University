package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        //count = groupeRepository.findById(idGroup).orElseThrow(()-> new IllegalArgumentException("Group not found")).getStudentList().size();
        Groups group = groupeRepository.findById(idGroup).orElseThrow(()-> new IllegalArgumentException("Group not found"));
        count = group.getStudentList().size();
        students.forEach(student ->
        {
            if(MAX_SIZE > count) {
                student.setGroup(group);
                studentRepository.save(student);
                count++;
            }
            else {
                LOGGER.debug("Limit group is 10 students");
                throw new IllegalArgumentException("Limit group is 10 students");
            }
        });
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
