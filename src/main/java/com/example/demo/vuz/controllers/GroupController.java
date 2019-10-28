package com.example.demo.vuz.controllers;


import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.model.Group;
import com.example.demo.vuz.model.Student;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.StudentRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("")
public class GroupController {

    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final GroupeRepository groupeRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public GroupController(DemoApplication.InMemoryStorage inMemoryStorage,
                           GroupeRepository groupeRepository,
                           StudentRepository studentRepository, TeacherRepository teacherRepository){
        this.inMemoryStorage = inMemoryStorage;
        this.groupeRepository = groupeRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }


    /*@GetMapping("/teachers")
    public List<Teacher> getListTeacher(){
        return teacherRepository.findAll();
    }

    @GetMapping("/teachers/{teacherId}")
    public Teacher getTeacher(@PathVariable (name = "teacherId") int teacherId){
        return inMemoryStorage.getTeacherById(teacherId);

    }

    @PostMapping("/teachers")
    public void createTeacher(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(firstName);
        newTeacher.setLastName(lastName);
        newTeacher.setAge(Math.abs(new Random().nextInt() % 95));
        newTeacher.setTeacherNumber(Math.abs(new Random().nextInt() % 1000000));

        teacherRepository.save(newTeacher);
    }*/


    @GetMapping("/groups")
    public List<Group> getListGroup(){
        return groupeRepository.findAll();
    }

    @GetMapping("/groups/{groupId}")
    public Group getGroup(@PathVariable (name = "groupId") int groupId){
        return inMemoryStorage.getGroupById(groupId);
    }

    @PostMapping("/groups")
    public void createGroup(@RequestParam("nameGroup") String name, @RequestParam(value = "studentList", required = false) List<Integer> studentsIds) {
        Group newGroup = new Group();
        newGroup.setName(name);

        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach( student -> student.setGroup(newGroup));
        newGroup.setStudentList(students);
        //newGroup.getStudentList();

        groupeRepository.save(newGroup);
    }

    @PostMapping("/groups/addStudent")
    @Transactional
    public void assignStudentToGroup(@RequestParam("GroupId") int groupId,
                           @RequestParam("studentList") List<Integer> studentsIds) throws Exception {
        Group group = groupeRepository.findById(groupId).orElseThrow(() -> new Exception("dada"));
        List<Student> students = studentRepository.findAllById(studentsIds);
        students.forEach(student -> student.setGroup(group));
       // group.setStudentList(students);
        groupeRepository.save(group);

//        if (Arrays.asList(groupeRepository.findAll()).contains(groupeRepository.findById(groupId))){
//            if(Arrays.asList(studentRepository.findAll()).contains(Arrays.asList(studentRepository.findAllById(studentsIds)))){
//                List<Student> studentsAdd = studentRepository.findAllById(studentsIds);
//                studentsAdd.forEach( student -> student.setGroup(groupeRepository.findById(groupId).get()));
//                groupeRepository.findById(groupId).get().setStudentList(studentsAdd);
//            }
//        }

    }
}