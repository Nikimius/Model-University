package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import jdk.internal.jline.internal.TestAccessible;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService {

    private final StudentRepository studentRepository;
    private final GroupeRepository groupeRepository;
    private final DepartmentRepository departmentRepository;

    public GroupService(StudentRepository studentRepository, GroupeRepository groupeRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.groupeRepository = groupeRepository;
        this.departmentRepository = departmentRepository;
    }


    public void createNewGroup(String name, List<Integer> studentsIds) {
        Groups newGroup = new Groups();
        newGroup.setName(name);
        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach(student -> student.setGroup(newGroup));
        newGroup.setStudentList(students);

        groupeRepository.save(newGroup);
    }



    public void removeGroups(List<Integer> groupsIds){
        List<Groups> groups = groupeRepository.findAllByIdIn(groupsIds);
        groups.forEach(this::removeGr);
    }

    // n + 1 performance problem
    private void removeGr(Groups group){
        List<Student> students = group.getStudentList();// no additional query ?
        students.forEach(student -> student.setGroup(null));
        groupeRepository.delete(group);
    }



    /* //удалит всех связанных с группой студентов
    public void delGroups(List<Integer> groupsIds){
        groupeRepository.deleteAllByIdIn(groupsIds);
    }*/

        //To delete one group

//    public void removeGroup(int groupId){
//        Group group = groupeRepository.findById(groupId).orElseThrow(()-> new IllegalArgumentException("Group not found"));
//        List<Student> students = group.getStudentList();
//        students.forEach(student -> student.setGroup(null));
//        groupeRepository.delete(group);
//
//    }

    public void changeToGroup(List<Integer> groupsIds, int depId){
        List<Groups> groups = groupeRepository.findAllById(groupsIds);
        groups.forEach(group -> group.setDepartment(departmentRepository.findById(depId)
                .orElseThrow(()->new IllegalArgumentException("Department not found"))));
        groups.forEach(group -> groupeRepository.save(group));
    }

}
