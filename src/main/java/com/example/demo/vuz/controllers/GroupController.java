package com.example.demo.vuz.controllers;

import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.dto.GroupDto;
import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import com.example.demo.vuz.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
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

    @PostMapping("/group")
    public void createGroupV2(@RequestBody GroupDto groupDto) {
        groupService.createNewGroup(groupDto.getName(), groupDto.getStudentsIds());
    }

    @PatchMapping("/group")
    public void addStudent(@RequestBody GroupDto groupDto) {
        groupService.addStudentsInGroups(groupDto.getGroupId(), groupDto.getStudentsIds());
    }

    @PatchMapping("/groups/maxSize")
    public void changeMaxSizeByGroup(@RequestBody GroupDto groupDto) {
        groupService.changeMaxSizeByGroup(groupDto.getGroupId(), groupDto.getMaxSize());
    }

    /*@PostMapping("/group")
    @Transactional
    public void createGroup(@RequestParam("nameGroup") String name,
                            @RequestParam(value = "studentList", required = false) List<Integer> studentsIds) {
        groupService.createNewGroup(name, studentsIds);
    }*/

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
    @DeleteMapping("/groups")
    public void delGroupsV2(@RequestBody GroupDto groupDto) {
        /*Удалит так же и всех студентов
        groupService.delGroups(groupDto.getGroupsIds());*/
        groupService.removeGroups(groupDto.getGroupsIds());
        //groupeRepository.deleteAllByIdIn(groupDto.getGroupsIds());
    }

    /*@PostMapping("/delGroups")
    @Transactional
    public void delGroups(@RequestParam("groupListIds") List<Integer> groupsIds){
        groupService.removeGroups(groupsIds);
    }
*/
    //To delete one group

//    @PostMapping("/delGroup")
//    @Transactional
//    public void delGroup(@RequestParam("groupId") int groupId){
//        groupService.removeGroup(groupId);
//    }

    @PatchMapping("/changeInGroupsOfTheDepartments")
    public void changeGroupV2(@RequestBody GroupDto groupDto) {
        groupService.changeToGroup(groupDto.getGroupsIds(), groupDto.getDepartmentId());
    }

    /*@PostMapping
    @Transactional
    public void changeGroup(@RequestParam("groupListIds") List<Integer> groupsIds,
                            @RequestParam("depId") int depId){
        groupService.changeToGroup(groupsIds, depId);
    }*/

    @GetMapping("/groups")
    public List<Groups> getListGroup() {
        return groupeRepository.findAll();
    }

    @GetMapping("/groups/{groupId}")
    public Groups getGroup(@PathVariable(name = "groupId") int groupId) {
        return inMemoryStorage.getGroupById(groupId);
    }
}