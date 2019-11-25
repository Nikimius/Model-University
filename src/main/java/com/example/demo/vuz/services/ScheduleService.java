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

    public void createSchedule(int dayOfWeek, String from, String to,int groupId, int subject, int teacherId, int classroomId) {
        Schedule newSchedule = new Schedule();
        newSchedule.setDay(Schedule.transformOfWeek(dayOfWeek));
        newSchedule.setFrom(from);
        newSchedule.setTo(to);
        newSchedule.setSubject(Schedule.transformSubject(subject));

        Groups group = groupeRepository.findById(groupId).orElseThrow(()-> new IllegalArgumentException("Not found group"));
        newSchedule.setGroup(group);

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new IllegalArgumentException("Not found teacher"));
        newSchedule.setTeacher(teacher);

        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(()-> new IllegalArgumentException("Not found classroom"));
        newSchedule.setClassroom(classroom);

        scheduleRepository.save(newSchedule);
    }


    public void changeDayOfWeek(int scheduleId, int dayOfWeek){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("Not found schedule"));
        schedule.setDay(Schedule.transformOfWeek(dayOfWeek));
    }

    public void changeFrom(int scheduleId, String from){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("Not found schedule"));
        schedule.setFrom(from);
    }

    public void changeTo(int scheduleId, String to) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Not found schedule"));
        schedule.setFrom(to);
    }

    public void changeGroupId(int scheduleId, int groupId){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("Not found schedule"));
        Groups group = groupeRepository.findById(groupId).orElseThrow(()-> new IllegalArgumentException("Not found group"));
        schedule.setGroup(group);
    }

    public void changeSubject(int scheduleId, int subject){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("Not found schedule"));
        schedule.setSubject(Schedule.transformSubject(subject));
    }

    public void changeTeacherId(int scheduleId, int teacherId){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("Not found schedule"));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new IllegalArgumentException("Not found teacher"));
        schedule.setTeacher(teacher);
    }

    public void changeClassroomId(int scheduleId, int classroomId){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("Not found schedule"));
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(()-> new IllegalArgumentException("Not found classroom"));
        schedule.setClassroom(classroom);
    }


}
