package com.example.demo.vuz.services;


import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Subject;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.SubjectRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, DepartmentRepository departmentRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Teacher createTeacher(String firstName, String lastName) {
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(firstName);
        newTeacher.setLastName(lastName);
        newTeacher.setAge(Math.abs(new Random().nextInt() % 101));
        newTeacher.setTeacherNumber(Math.abs(new Random().nextInt() % 1_010_000));

        return teacherRepository.save(newTeacher);
    }

    // TODO bad naming, removeTeachersByIdIn is better
    public void deleteTeachersByIdIn(List<Integer> teachersIds) {
        List<Teacher> teachers = teacherRepository.findAllById(teachersIds);
        teachers.forEach(teacherRepository::delete);
        //teachers.forEach(teacher -> teacherRepository.delete(teacher));
    }

    public Teacher updateTeacher(int idTeacher, Map<String, Object> dto) {
        Teacher teacher = teacherRepository.findById(idTeacher).orElseThrow(() -> new IllegalArgumentException("Not found teacher"));

        if (dto.containsKey("departmentId")) {
            int departmentId = (Integer) dto.get("departmentId");
            Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalArgumentException("Not found department"));
            teacher.setDepartment(department);
        }

        if (dto.containsKey("subjectsIds")) {
            @SuppressWarnings("unchecked")
            List<Integer> subjectsIds = (List<Integer>) dto.get("subjectsIds");
            List<Subject> subjects = subjectRepository.findAllById(subjectsIds);
            List<Subject> subjectsByGroup = teacher.getSubjects();

            List<Subject> subjectCopy = new ArrayList<>(subjects);
            subjectCopy.removeAll(subjectsByGroup);
            subjectsByGroup.addAll(subjectCopy);

            teacher.setSubjects(subjectsByGroup);
        }

        if (dto.containsKey("firstName")) {
            String firstName = (String) dto.get("firstName");
            teacher.setFirstName(firstName);
        }

        if (dto.containsKey("lastName")) {
            String lastName = (String) dto.get("lastName");
            teacher.setFirstName(lastName);
        }

        return teacherRepository.save(teacher);
    }
}
