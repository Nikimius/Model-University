package com.example.demo.vuz.services;

import com.example.demo.vuz.UniverJpaConfig;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {UniverJpaConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class StudentServiceTest {

    @Resource
    private StudentRepository studentRepository;

    @Test
    public void createGivenStudent_whenSave_thenGetOk() {
        Student student = new Student("Sergei", "Savinov", 23, 123465);
        studentRepository.save(student);

        Student student2 = studentRepository.findById(1).orElseThrow(()-> new IllegalArgumentException("Student not found"));
        assertEquals("Savinov", student2.getLastName());
    }
}