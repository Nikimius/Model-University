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

    public void createRoomClass(int numberClassroom, int maxSize) {
        Classroom newClassroom = new Classroom();
        newClassroom.setNumberClassroom(numberClassroom);
        newClassroom.setMaxSize(maxSize);

        classroomRepository.save(newClassroom);
    }

    /*public void removeRoomClass(List<Integer> classroomsIds){
        List<Classroom> classrooms = classroomRepository.findAllById(classroomsIds);
        classrooms.forEach(classroom -> classroomRepository.delete(classroom));
    }*/

    public void removeRoomClass(List<Integer> classroomsIds) {
        classroomRepository.deleteAllByIdIn(classroomsIds);
    }

    public void changeClassroom(Map<String, Integer> dto) {
        if (dto.containsKey("classroomId")){
            int classroomId = dto.get("classroomId");
            Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new IllegalArgumentException("Not found classroom"));

            if (dto.containsKey("numberClassroom")) {
                classroom.setNumberClassroom(dto.get("numberClassroom"));
            }

            if (dto.containsKey("maxSize")) {
                classroom.setMaxSize(dto.get("maxSize"));
            }

            classroomRepository.save(classroom);
        } else System.out.println("Not found classroom");
    }

    /*public void changeMaxSize(int classroomId, int newMaxSize){
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(()-> new IllegalArgumentException("Not found classroom"));
        classroom.setMaxSize(newMaxSize);

        classroomRepository.save(classroom);
    }

    public void changeNumberClassroom(int classroomId, int numberClassroom){
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(()-> new IllegalArgumentException("Not found classroom"));
        classroom.setNumberClassroom(numberClassroom);

        classroomRepository.save(classroom);
    }*/
}
