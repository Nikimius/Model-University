package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceMockitoTest {


    private final List<Integer> list1 = Collections.unmodifiableList(Collections.singletonList(1));
    private final Student student = new Student();
    private final int idGroup = 24;
    private final Groups group = new Groups();

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private GroupeRepository groupeRepository;

    @Before
    public void setUp() {
        student.setGroup(new Groups());
    }

    @Test
    public void testRemoveStudentsFromGroup() {
        when(studentRepository.findAllById(list1)).thenReturn(Collections.singletonList(student));
        assertNotNull(student.getGroup());

        studentService.removeStudentsFromGroup(list1);
        assertNull(student.getGroup());

        verify(studentRepository).save(student);
    }

    @Test
    public void testCreateStudents() {
        Student student1 = studentService.createStudents("Sergey", "Savinov");
        assertEquals("Sergey", student1.getFirstName());
        assertEquals("Savinov", student1.getLastName());

        verify(studentRepository).save(student1);
    }

    @Test
    public void testStudentChangeGroup() {
        when(studentRepository.findAllById(list1)).thenReturn(Collections.singletonList(student));
        assertNotNull(student.getGroup());

        when(groupeRepository.findById(idGroup)).thenReturn(Optional.of(group));
        studentService.studentChangeGroup(list1, idGroup);
        assertEquals(group, student.getGroup());

        verify(studentRepository).save(student);
    }

    @Test
    public void testRemoveStudent() {
        studentService.removeStudent(list1);
        verify(studentRepository).deleteAllByIdIn(list1);
    }


}
