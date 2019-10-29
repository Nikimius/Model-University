package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
<<<<<<< HEAD
import java.util.Random;
=======
>>>>>>> e41799a1a952907787b1a652b266920f78ec3453

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupeRepository groupeRepository;


    public StudentService(StudentRepository studentRepository, GroupeRepository groupeRepository) {
        this.studentRepository = studentRepository;
        this.groupeRepository = groupeRepository;
    }
<<<<<<< HEAD

=======
>>>>>>> e41799a1a952907787b1a652b266920f78ec3453
//  Для одного элемента
    /*public void removeStudent(Integer studentId){
        studentRepository.delete(studentId);
    }*/

<<<<<<< HEAD
    //  Для списка элементов
    public void removeStudent(List<Integer> studentsIds) {
        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach(student -> studentRepository.delete(student));
    }

    public void studentChangeGroup(List<Integer> studentsIds, int idGroup) {
        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach(student -> student.setGroup(groupeRepository.findById(idGroup).orElseThrow(() -> new IllegalArgumentException("Group not found"))));
        students.forEach(student -> studentRepository.save(student));
    }

    public void createStudents(String fistName, String lastName){
        Student newStudent = new Student();
        newStudent.setFirstName(fistName);
        newStudent.setLastName(lastName);
        newStudent.setAge(Math.abs(new Random().nextInt() % 100));
        newStudent.setStudentNumber(Math.abs(new Random().nextInt() % 1000_000));

        studentRepository.save(newStudent);
=======
//  Для списка элементов
    public void removeStudent(List<Integer> studentsIds){
        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach(student -> studentRepository.delete(student));
    }

    public void studentChangeGroup(int idStudent, int idGroup){
        studentRepository.findById(idStudent).orElseThrow(()-> new IllegalArgumentException("Student not found"))
                .setGroup(groupeRepository.findById(idGroup).orElseThrow(()-> new IllegalArgumentException("Group not found")));
        studentRepository.save(studentRepository.findById(idStudent).get());
>>>>>>> e41799a1a952907787b1a652b266920f78ec3453
    }

    public void removeStudentsFromGroup(List<Integer> stIds){
        List<Student> students = studentRepository.findAllById(stIds);
        students.forEach(student -> student.setGroup(null));
        students.forEach(student -> studentRepository.save(student));
    }

}
