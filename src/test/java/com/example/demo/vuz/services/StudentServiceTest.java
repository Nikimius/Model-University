package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.StudentRepository;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackageClasses = StudentService.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    //по сути проверка save()
    @Test
    public void createGivenStudent_whenSave_thenGetOk() {
        Student student1 = new Student("Sergei", "Savinov", 23, 123465);
        Student savedStudent = studentRepository.save(student1);

        Student student2 = studentRepository.findById(savedStudent.getId()).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        assertEquals("Savinov", student2.getLastName());
    }

    @Test
    public void removeStudent_test(){
        int id = 24;
        Student student = new Student("Sergei", "Savinov", 23, 123465);
        student.setId(id);
        System.out.println(studentRepository.findAll());
        assertNotNull(student.getId());
        studentRepository.save(student);

        studentService.removeStudent(Arrays.asList(id));

        assertEquals(Arrays.asList(), studentRepository.findAllById(Arrays.asList(id)));
    }

}

















