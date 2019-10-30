package com.example.demo.vuz.services;


import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }


    public void createTeachers(String firstName, String lastName){
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(firstName);
        newTeacher.setLastName(lastName);
        newTeacher.setAge(Math.abs(new Random().nextInt() % 101));
        newTeacher.setTeacherNumber(Math.abs(new Random().nextInt() % 1_010_000));

        teacherRepository.save(newTeacher);
    }

    public void removeTeachers(List<Integer> teachersIds){
        List<Teacher> teachers = teacherRepository.findAllById(teachersIds);
        teachers.forEach(teacher -> teacherRepository.delete(teacher));
    }

    public void changeDepartmentToTeacher(List<Integer> teachersIds, int departmentId){
        List<Teacher> teachers = teacherRepository.findAllById(teachersIds);
        teachers.forEach(teacher -> teacher.setDepartment(departmentRepository.findById(departmentId).orElseThrow(()-> new IllegalArgumentException("Department not found"))));
        teachers.forEach(teacher -> teacherRepository.save(teacher));
    }

    public void removeTeacherFromGroup(List<Integer> thIds){
        List<Teacher> teachers = teacherRepository.findAllById(thIds);
        teachers.forEach(teacher -> teacher.setDepartment(null));
        teachers.forEach(teacher -> teacherRepository.save(teacher));
    }

}
