package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Group;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final StudentRepository studentRepository;
    private final GroupeRepository groupeRepository;

    public GroupService(StudentRepository studentRepository, GroupeRepository groupeRepository) {
        this.studentRepository = studentRepository;
        this.groupeRepository = groupeRepository;
    }


    public void createNewGroup(String name, List<Integer> studentsIds) {
        Group newGroup = new Group();
        newGroup.setName(name);
        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach(student -> student.setGroup(newGroup));
        newGroup.setStudentList(students);

        groupeRepository.save(newGroup);
    }


    public void removeGroups(List<Integer> groupsIds){
        List<Group> groups = groupeRepository.findAllById(groupsIds);
        groups.forEach(group -> removeGr(group));
    }

    public void removeGr(Group group){
        List<Student> students = group.getStudentList();
        students.forEach(student -> student.setGroup(null));
        groupeRepository.delete(group);
    }
        //To delete one group

//    public void removeGroup(int groupId){
//        Group group = groupeRepository.findById(groupId).orElseThrow(()-> new IllegalArgumentException("Group not found"));
//        List<Student> students = group.getStudentList();
//        students.forEach(student -> student.setGroup(null));
//        groupeRepository.delete(group);
//
//    }


}
