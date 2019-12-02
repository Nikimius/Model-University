package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Classroom;
import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.model.Schedule;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.ClassroomRepository;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.ScheduleRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupeRepository groupeRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, GroupeRepository groupeRepository, TeacherRepository teacherRepository, ClassroomRepository classroomRepository) {
        this.scheduleRepository = scheduleRepository;
        this.groupeRepository = groupeRepository;
        this.teacherRepository = teacherRepository;
        this.classroomRepository = classroomRepository;
    }

    public void createSchedule(int dayOfWeek, String from, String to, int groupId, int subject, int teacherId, int classroomId) {
        Schedule newSchedule = new Schedule();
        newSchedule.setDay(Schedule.transformDayOfWeek(dayOfWeek));
        newSchedule.setFrom(from);
        newSchedule.setTo(to);
        newSchedule.setSubject(Schedule.transformSubject(subject));

        Groups group = groupeRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Not found group"));
        newSchedule.setGroup(group);

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalArgumentException("Not found teacher"));
        newSchedule.setTeacher(teacher);

        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new IllegalArgumentException("Not found classroom"));
        newSchedule.setClassroom(classroom);

        scheduleRepository.save(newSchedule);
    }

    public void changeSchedule(Map<String, String> dto) {
        if (dto.containsKey("scheduleId")) {
            Integer scheduleId = Integer.parseInt(dto.get("scheduleId"));
            Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Not found schedule"));

            if (dto.containsKey("dayOfWeek")) {
                schedule.setDay(Schedule.transformDayOfWeek(Integer.parseInt(dto.get("dayOfWeek"))));
            }

            if (dto.containsKey("groupId")) {
                int groupId = Integer.parseInt(dto.get("groupId"));
                Groups group = groupeRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Not found group"));
                schedule.setGroup(group);
            }

            if (dto.containsKey("subject")) {
                schedule.setSubject(Schedule.transformSubject(Integer.parseInt(dto.get("subject"))));
            }

            if (dto.containsKey("teacherId")) {
                int teacherId = Integer.parseInt(dto.get("teacherId"));
                Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalArgumentException("Not found teacher"));
                schedule.setTeacher(teacher);
            }

            if (dto.containsKey("classroomId")) {
                int classroomId = Integer.parseInt(dto.get("classroomId"));
                Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new IllegalArgumentException("Not found classroom"));
                schedule.setClassroom(classroom);
            }

            if(dto.containsKey("from")){
                schedule.setFrom(dto.get("from"));
            }

            if(dto.containsKey("to")){
                schedule.setTo(dto.get("to"));
            }

            scheduleRepository.save(schedule);
        } else System.out.println("Not found schedule");
    }


    public void deleteScheduleById(List<Integer> scheduleIds) {
        scheduleRepository.deleteAllByIdIn(scheduleIds);
    }

}
