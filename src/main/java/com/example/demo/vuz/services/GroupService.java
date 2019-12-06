package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.model.Subject;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import com.example.demo.vuz.repositories.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GroupService {

    private final StudentRepository studentRepository;
    private final GroupeRepository groupeRepository;
    private final DepartmentRepository departmentRepository;
    private final SubjectRepository subjectRepository;

    public GroupService(StudentRepository studentRepository, GroupeRepository groupeRepository, DepartmentRepository departmentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.groupeRepository = groupeRepository;
        this.departmentRepository = departmentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Groups createGroup(String name, List<Integer> studentsIds, List<Integer> subjectsIds) {
        Groups newGroup = new Groups();
        newGroup.setName(name);
        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach(student -> newGroup.addNewStudent(student));
        newGroup.setStudentList(students);

        List<Subject> subjects = subjectRepository.findAllById(studentsIds);
        newGroup.setListSubjects(subjects);

        return  groupeRepository.save(newGroup);
    }

    public Groups updateGroup(int id, Map<String, Object> dto) {
        Groups group = groupeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Group not found"));

        if (dto.containsKey("name")) {
            group.setName((String) dto.get("name"));
        }

        if (dto.containsKey("studentsIds")) {
            @SuppressWarnings("unchecked")
            List<Integer> studentsIds = (List<Integer>) dto.get("studentsIds");
            List<Student> students = studentRepository.findAllById(studentsIds);
            students.forEach(student -> {
                group.addStudentInGroup(student);
                studentRepository.save(student);
            });
        }

        if (dto.containsKey("subjectsIds")) {
            @SuppressWarnings("unchecked")
            List<Integer> subjectsIds = (List<Integer>) dto.get("subjectsIds");
            List<Subject> subjects = subjectRepository.findAllById(subjectsIds);
            List<Subject> subjectsByGroup = group.getListSubjects();

            List<Subject> subjectCopy = new ArrayList<>(subjects);
            subjectCopy.removeAll(subjectsByGroup);
            subjectsByGroup.addAll(subjectCopy);

            group.setListSubjects(subjectsByGroup);
        }

        if (dto.containsKey("departmentId")) {
            int departmentId = (Integer) dto.get("departmentId");
            Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalArgumentException("Department not found"));
            group.setDepartment(department);
        }

        if (dto.containsKey("maxSize")) {
            int maxSize = (Integer) dto.get("maxSize");
            group.setMaxSize(maxSize);
        }

        return groupeRepository.save(group);
    }

    public void deleteGroupsByIdIn(List<Integer> groupsIds) {
        List<Groups> groups = groupeRepository.findAllByIdIn(groupsIds);
        groups.forEach(groupeRepository::delete);
    }
}
