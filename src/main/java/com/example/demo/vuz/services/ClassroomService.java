package com.example.demo.vuz.services;


import com.example.demo.vuz.model.Classroom;
import com.example.demo.vuz.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    // TODO RoomClass or classRoom ? stick with one
    public Classroom createRoomClass(int numberClassroom, int maxSize) {
        Classroom newClassroom = new Classroom();
        newClassroom.setNumberClassroom(numberClassroom);
        newClassroom.setMaxSize(maxSize);

        return classroomRepository.save(newClassroom);
    }

    // TODO give a better name to the method like: deleteClassRoomsByIdIn...
    public void deleteClassroomsByIdIn(List<Integer> classroomsIds) {
        classroomRepository.deleteAllByIdIn(classroomsIds);
    }

    // TODO update classroom is a better name
    public Classroom updateClassroom(int id, Map<String, Integer> dto) {
        Classroom classroom = classroomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found classroom"));
        if (dto.containsKey("numberClassroom")) {
            classroom.setNumberClassroom(dto.get("numberClassroom"));
        }
        if (dto.containsKey("maxSize")) {
            classroom.setMaxSize(dto.get("maxSize"));
        }
        return classroomRepository.save(classroom);
    }
}
