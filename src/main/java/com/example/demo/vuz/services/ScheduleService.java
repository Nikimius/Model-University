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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    // TODO - relatively many arguments, think of a design pattern to use in this case.
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

            if (dto.containsKey("from")) {
                schedule.setFrom(dto.get("from"));
            }

            if (dto.containsKey("to")) {
                schedule.setTo(dto.get("to"));
            }

            scheduleRepository.save(schedule);
        } else System.out.println("Not found schedule");
    }

    public void deleteScheduleById(List<Integer> scheduleIds) {
        scheduleRepository.deleteAllByIdIn(scheduleIds);
    }

    //only correct IDs of parameters schedule!!!
    public void generateSchedule(boolean param) {
        if (param) {
            for (int i = 1; i <= 5; i++) {
                if (i == 1) {
                    generateOneLesson("8:00", "09:45");
                } else if (i == 2) {
                    generateOneLesson("10:00", "11:45");
                } else if (i == 3) {
                    generateOneLesson("12:00", "13:45");
                } else if (i == 4) {
                    generateOneLesson("14:00", "15:45");
                } else if (i == 5) {
                    generateOneLesson("16:00", "17:45");
                }
            }
        }
    }

    public void generateOneLesson(String from, String to) {
        List<Integer> listSubjects = new ArrayList<>();
        List<Integer> listGroup = new ArrayList<>();
        List<Integer> listTeacher = new ArrayList<>();
        List<Integer> listClassroom = new ArrayList<>();
        for (int j = 1; j <= 5; j++) {

            Schedule schedule = new Schedule();
            schedule.setDay(Schedule.transformDayOfWeek(1));
            schedule.setFrom(from);
            schedule.setTo(to);

            int randomSubject = new Random().nextInt(5) + 1;
            int newRandomSubject = validRandomNum(randomSubject, listSubjects, 5);
            schedule.setSubject(Schedule.transformSubject(newRandomSubject));
            listSubjects.add(newRandomSubject);

            int randomGroup = new Random().nextInt(6) + 1;
            int newRandomGroup = validRandomNum(randomGroup, listGroup, 6);
            Groups group = groupeRepository.findById(newRandomGroup).orElseThrow(() -> new IllegalArgumentException("Not found group"));
            schedule.setGroup(group);
            listGroup.add(newRandomGroup);

            int randomTeacher = new Random().nextInt(11) + 1;
            int newRandomTeacher = validRandomNum(randomTeacher, listTeacher, 11);
            Teacher teacher = teacherRepository.findById(newRandomTeacher).orElseThrow(() -> new IllegalArgumentException("Not found teacher"));
            schedule.setTeacher(teacher);
            listTeacher.add(newRandomTeacher);

            int randomClassroom = new Random().nextInt(7) + 1;
            int newRandomClassroom = validRandomNum(randomClassroom, listClassroom, 7);
            Classroom classroom = classroomRepository.findById(newRandomClassroom).orElseThrow(() -> new IllegalArgumentException("Not found classroom"));
            schedule.setClassroom(classroom);
            listClassroom.add(newRandomClassroom);

            scheduleRepository.save(schedule);
        }
    }

    public int validRandomNum(int randomNum, List<Integer> listSubjects, int max) {
        if (!listSubjects.contains(randomNum)) {
            return randomNum;
        } else {
            randomNum = new Random().nextInt(max) + 1;
            return validRandomNum(randomNum, listSubjects, max);
        }
    }

}
