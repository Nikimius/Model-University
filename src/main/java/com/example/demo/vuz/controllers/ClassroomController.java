package com.example.demo.vuz.controllers;


import com.example.demo.vuz.dto.ClassroomDto;
import com.example.demo.vuz.services.ClassroomService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping("/classrooms")
    public void creatClassroom(@RequestBody ClassroomDto classroomDto) {
        classroomService.createRoomClass(classroomDto.getNumberClassroom(), classroomDto.getMaxSize());
    }

    @DeleteMapping("/classrooms")
    public void removeClassroom(@RequestBody ClassroomDto classroomDto) {
        classroomService.removeRoomClass(classroomDto.getClassroomsIds());
    }

    @PatchMapping("/classroom/maxSize")
    public void changeMaxSizeClassroom(@RequestBody ClassroomDto classroomDto){
        classroomService.changeMaxSize(classroomDto.getClassroomId(), classroomDto.getMaxSize());
    }

    @PatchMapping("/classroom/numberClassroom")
    public void changeNumberClassroom(@RequestBody ClassroomDto classroomDto){
        classroomService.changeNumberClassroom(classroomDto.getClassroomId(), classroomDto.getNumberClassroom());
    }


}
