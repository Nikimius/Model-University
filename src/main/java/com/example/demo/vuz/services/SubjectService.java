package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.model.Subject;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.SubjectRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SubjectService {

    @Transient
    private final static Logger LOGGER = LoggerFactory.getLogger(GroupService.class.getName());

    @Transient
    private final static java.util.logging.Logger log = java.util.logging.Logger.getLogger(Groups.class.getName());

    private final SubjectRepository subjectRepository;
    private final GroupeRepository groupRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, GroupeRepository groupRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
    }

    public Subject createSubject(String name) {
        Subject subject = new Subject();
        subject.setName(name);

        return subjectRepository.save(subject);
    }

    public Subject updateSubject(int subjectId, Map<String, Object> dto) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("Not found subject"));

        if (dto.containsKey("name")) {
            String name = (String) dto.get("name");
            subject.setName(name);
        }

        if (dto.containsKey("groupsIds")) {
            @SuppressWarnings("unchecked")
            List<Integer> groupsIds = (List<Integer>) dto.get("groupsIds");
            List<Groups> groups = groupRepository.findAllById(groupsIds);
            groups.forEach(group -> {
                List<Subject> subjectList = group.getListSubjects();
                if(!subjectList.contains(subject)) {
                    subjectList.add(subject);
                    group.setListSubjects(subjectList);
                    groupRepository.save(group);
                }
                else {
                    System.out.println("This " + group.getName() + " is already in the list of items");
                }
            });
        }

        if (dto.containsKey("teachersIds")) {
            @SuppressWarnings("unchecked")
            List<Integer> teachersIds = (List<Integer>) dto.get("teachersIds");
            List<Teacher> teachers = teacherRepository.findAllById(teachersIds);
            teachers.forEach(teacher -> {
                List<Subject> subjectList = teacher.getSubjects();
                if(!subjectList.contains(subject)) {
                    subjectList.add(subject);
                    teacher.setSubjects(subjectList);
                    teacherRepository.save(teacher);
                }
                else {
                    System.out.println("This " + teacher.getFirstName() + " " + teacher.getLastName() + " is already in the list of items");
                }
            });
        }

        return subjectRepository.save(subject);
    }

    public void deleteSubjectByIdIn(int subjectId, Map<String, Integer> dto) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("Subject not found"));

        if (dto.containsKey("groupId")) {
            int groupId = dto.get("groupId");
            Groups group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Group not found"));
            List<Subject> subjects = group.getListSubjects();
            subjects.remove(subject);
            group.setListSubjects(subjects);

            groupRepository.save(group);
        }

        if (dto.containsKey("teacherId")) {
            int teacherId = dto.get("teacherId");
            Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
            List<Subject> subjects = teacher.getSubjects();
            subjects.remove(subject);
            teacher.setSubjects(subjects);

            teacherRepository.save(teacher);
        }
    }

    public void deleteSubjectById(List<Integer> subjectIds) {
        List<Subject> subjects = subjectRepository.findAllById(subjectIds);
        subjects.forEach(subjectRepository::delete);
    }
}
