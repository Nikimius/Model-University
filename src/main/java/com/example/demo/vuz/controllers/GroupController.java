package com.example.demo.vuz.controllers;


import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.model.Group;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import com.example.demo.vuz.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class GroupController {

    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final GroupeRepository groupeRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GroupService groupService;

    @Autowired
    public GroupController(DemoApplication.InMemoryStorage inMemoryStorage,
                           GroupeRepository groupeRepository,
                           StudentRepository studentRepository, TeacherRepository teacherRepository, GroupService groupService) {
        this.inMemoryStorage = inMemoryStorage;
        this.groupeRepository = groupeRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public List<Group> getListGroup() {
        return groupeRepository.findAll();
    }

    @GetMapping("/groups/{groupId}")
    public Group getGroup(@PathVariable(name = "groupId") int groupId) {
        return inMemoryStorage.getGroupById(groupId);
    }

    @PostMapping("/group")
    @Transactional
    public void createGroup(@RequestParam("nameGroup") String name,
                            @RequestParam(value = "studentList", required = false) List<Integer> studentsIds) {
        groupService.createNewGroup(name, studentsIds);
    }

    // Данный функционал выполняет фукнция studentChangeGroup in StudentService
    /*@PostMapping("/groups/addStudent")
    @Transactional
    public void assignStudentToGroup(@RequestParam("GroupId") int groupId,
                           @RequestParam("studentList") List<Integer> studentsIds) throws Exception {
        Group group = groupeRepository.findById(groupId).orElseThrow(() -> new Exception("dada"));
        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach(student -> student.setGroup(group));
       // group.setStudentList(students);
        groupeRepository.save(group);
    }*/

    @PostMapping("/delGroups")
    @Transactional
    public void delGroups(@RequestParam("groupListIds") List<Integer> groupsIds){
        groupService.removeGroups(groupsIds);
    }

    //To delete one group

//    @PostMapping("/delGroup")
//    @Transactional
//    public void delGroup(@RequestParam("groupId") int groupId){
//        groupService.removeGroup(groupId);
//    }

    @PostMapping
    @Transactional
    public void changeGroup(@RequestParam("groupListIds") List<Integer> groupsIds,
                            @RequestParam("depId") int depId){
        groupService.changeToGroup(groupsIds, depId);
    }
}