package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceMockitoTest {

    private final List<Integer> studentsIds = Collections.unmodifiableList(Collections.singletonList(1));
    private final int studentId = 1;
    private final Student student = new Student();
    private final Groups group = new Groups();

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Before
    public void setUp() {
        student.setGroup(new Groups());
    }

    @Test
    public void testRemoveStudentsFromGroup() {
        when(studentRepository.findAllById(studentsIds)).thenReturn(Collections.singletonList(student));
        List<Student>  students = studentRepository.findAllById(studentsIds);
        students.forEach(studentTest -> assertNotNull(studentTest.getGroup()));

        studentService.deleteStudentsFromGroup(studentsIds);
        students.forEach(studentTest -> assertNull(studentTest.getGroup()));

        verify(studentRepository).save(student);
    }

    @Test
    public void testCreateStudents() {
        Student student1 = studentService.createStudent("Sergey", "Savinov");
        assertEquals("Sergey", student1.getFirstName());
        assertEquals("Savinov", student1.getLastName());

        verify(studentRepository).save(student1);
    }

    @Test
    public void testStudentChangeGroup() {
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        student.setFirstName("Sergey");

        Student studentTest = studentRepository.findById(studentId).orElseThrow(()-> new IllegalArgumentException("Student not found"));
        Map<String, String> dto = new HashMap<>();
        dto.put("firstName", "Alexandr");
        studentService.updateStudentById(studentId, dto);
        assertNotEquals("Sergey", studentTest.getFirstName());

        verify(studentRepository).save(studentTest);
    }

    @Test
    public void testRemoveStudent() {
        when(studentRepository.findAllById(studentsIds)).thenReturn(Collections.singletonList(student));
        List<Student>  students = studentRepository.findAllById(studentsIds);
        assertNotNull(students);

        studentService.deleteStudentsById(studentsIds);
        //assertEquals(Collections.emptyList(), students);
        verify(studentRepository).deleteAllByIdIn(studentsIds);
    }
}
