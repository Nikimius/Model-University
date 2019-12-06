package com.example.demo.vuz.controllers;


import com.example.demo.vuz.dto.ClassroomDto;
import com.example.demo.vuz.model.Classroom;
import com.example.demo.vuz.services.ClassroomService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping()
    public Classroom creatClassroom(@RequestBody ClassroomDto classroomDto) {
        return classroomService.createRoomClass(classroomDto.getNumberClassroom(), classroomDto.getMaxSize());
    }

    @DeleteMapping()
    public void deleteClassroom(@RequestBody ClassroomDto classroomDto) {
        classroomService.deleteClassroomsByIdIn(classroomDto.getClassroomsIds());
    }

    @PatchMapping("/{id}")
    public Classroom updateClassroom(@PathVariable int id, @RequestBody Map<String, Integer> objDto) {
        return classroomService.updateClassroom(id, objDto);
    }
}
