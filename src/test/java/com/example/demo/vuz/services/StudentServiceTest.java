package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void createGivenStudent_whenSave_thenGetOk() {
        Student student = new Student("Sergei", "Savinov", 23, 123465);
        studentRepository.save(student);

        Student student2 = studentRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        assertEquals("Savinov", student2.getLastName());
    }
}

















