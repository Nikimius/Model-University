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

import java.util.Map;

@RestController
@RequestMapping("/groups")
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

    @PostMapping()
    public Groups createGroupV2(@RequestBody GroupDto groupDto) {
        return  groupService.createGroup(groupDto.getName(), groupDto.getStudentsIds(), groupDto.getSubjectsIds());
    }

    @PatchMapping("/{id}")
    public Groups updateGroup(@PathVariable int id, @RequestBody Map<String, Object> groupDto) {
        return groupService.updateGroup(id, groupDto);
    }

    @DeleteMapping()
    public void deleteGroup(@RequestBody GroupDto groupDto) {
        groupService.deleteGroupsByIdIn(groupDto.getGroupsIds());
    }

    // /groups
    // /groups?s=12143891729
    /*@GetMapping("/groups")
    public List<Groups> getListGroup(@RequestParam(required = false) Long since) {

        if (since != null) {
            groupeRepository.findAllSince(since);
        } else {
            groupeRepository.findAll();
        }
    }*/

    @GetMapping("/{groupId}")
    public Groups getGroup(@PathVariable(name = "groupId") int groupId) {
        return inMemoryStorage.getGroupById(groupId);
    }
}