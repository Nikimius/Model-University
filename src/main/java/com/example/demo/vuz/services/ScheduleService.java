package com.example.demo.vuz.services;

import com.example.demo.vuz.dto.ScheduleDto;
import com.example.demo.vuz.model.*;
import com.example.demo.vuz.repositories.*;
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
    private final SubjectRepository subjectRepository;
    private Random randomGenerator = new Random();

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, GroupeRepository groupeRepository,
                           TeacherRepository teacherRepository, ClassroomRepository classroomRepository,
                           SubjectRepository subjectRepository) {
        this.scheduleRepository = scheduleRepository;
        this.groupeRepository = groupeRepository;
        this.teacherRepository = teacherRepository;
        this.classroomRepository = classroomRepository;
        this.subjectRepository = subjectRepository;
    }
    // TODO - relatively many arguments, think of a design pattern to use in this case.
    /*public Schedule createSchedule(int dayOfWeek, String from, String to, int groupId, int subjectId, int teacherId, int classroomId) {
        Schedule newSchedule = new Schedule();
        newSchedule.setDay(Schedule.transformDayOfWeek(dayOfWeek));
        newSchedule.setFrom(from);
        newSchedule.setTo(to);

        Groups group = groupeRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Not found group"));
        newSchedule.setGroup(group);

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(()-> new IllegalArgumentException("Not found subject"));
        newSchedule.setSubject(subject);

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalArgumentException("Not found teacher"));
        newSchedule.setTeacher(teacher);

        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new IllegalArgumentException("Not found classroom"));
        newSchedule.setClassroom(classroom);

        return scheduleRepository.save(newSchedule);
    }*/

    public Schedule createSchedule(ScheduleDto dto) {
        Schedule newSchedule = new Schedule();
        newSchedule.setDay(Schedule.transformDayOfWeek(dto.getDayOfWeek()));
        newSchedule.setFrom(dto.getFrom());
        newSchedule.setTo(dto.getTo());

        Groups group = groupeRepository.findById(dto.getGroupId()).orElseThrow(() -> new IllegalArgumentException("Not found group"));
        newSchedule.setGroup(group);

        Subject subject = subjectRepository.findById(dto.getSubjectId()).orElseThrow(()-> new IllegalArgumentException("Not found subject"));
        newSchedule.setSubject(subject);

        Teacher teacher = teacherRepository.findById(dto.getTeacherId()).orElseThrow(() -> new IllegalArgumentException("Not found teacher"));
        newSchedule.setTeacher(teacher);

        Classroom classroom = classroomRepository.findById(dto.getClassroomId()).orElseThrow(() -> new IllegalArgumentException("Not found classroom"));
        newSchedule.setClassroom(classroom);

        return scheduleRepository.save(newSchedule);
    }

    public Schedule updateSchedule(int scheduleId, Map<String, String> dto) {
            Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Not found schedule"));

            if (dto.containsKey("dayOfWeek")) {
                schedule.setDay(Schedule.transformDayOfWeek(Integer.parseInt(dto.get("dayOfWeek"))));
            }

            if (dto.containsKey("groupId")) {
                int groupId = Integer.parseInt(dto.get("groupId"));
                Groups group = groupeRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Not found group"));
                schedule.setGroup(group);
            }

            if (dto.containsKey("subjectId")) {
                int subjectId = Integer.parseInt(dto.get("subjectId"));
                Subject subject = subjectRepository.findById(subjectId).orElseThrow(()-> new IllegalArgumentException("Not found subject"));
                schedule.setSubject(subject);
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

            return scheduleRepository.save(schedule);
    }

    public void deleteScheduleById(List<Integer> scheduleIds) {
        scheduleRepository.deleteAllByIdIn(scheduleIds);
    }

    //only correct IDs of parameters schedule!!!
    public List<Schedule> generateSchedule() {
         List<Schedule> scheduleList = new ArrayList<>();
         generateOneLesson("8:00", "09:45", scheduleList);
         generateOneLesson("10:00", "11:45", scheduleList);
         generateOneLesson("12:00", "13:45", scheduleList);
         generateOneLesson("14:00", "15:45", scheduleList);
         generateOneLesson("16:00", "17:45", scheduleList);
         return scheduleList;
    }

    public void generateOneLesson(String from, String to, List<Schedule> scheduleList) {
        List<Integer> subjectsIds = new ArrayList<>();
        List<Integer> listGroup = new ArrayList<>();
        List<Integer> teachersIds = new ArrayList<>();
        List<Integer> listClassroom = new ArrayList<>();

        for (int j = 1; j <= 5; j++) {

            Schedule schedule = new Schedule();
            schedule.setDay(Schedule.transformDayOfWeek(1));
            schedule.setFrom(from);
            schedule.setTo(to);

            int randomGroup = randomGenerator.nextInt(6) + 1;
            int newRandomGroup = validRandomNum(randomGroup, listGroup, 6);
            Groups group = groupeRepository.findById(newRandomGroup).orElseThrow(() -> new IllegalArgumentException("Not found group"));
            schedule.setGroup(group);
            listGroup.add(newRandomGroup);

            List<Subject> subjectList = group.getListSubjects();
            /*non-id random, it's index*/
            int randomIndexSubject = new Random().nextInt(subjectList.size());
            /*get random subject by group*/
            Subject subject = subjectList.get(randomIndexSubject);
            /*get random id's subject*/
            int randomSubjectId = subject.getId();
            int newRandomSubject = validRandomNumForSubject(randomSubjectId, subjectsIds, subjectList);
            schedule.setSubject(subject);
            subjectsIds.add(newRandomSubject);

            Subject subjectForTeacher = subjectRepository.findById(newRandomSubject).orElseThrow(()-> new IllegalArgumentException("Subject not found"));
            List<Teacher> teacherList = subjectForTeacher.getTeacherList();
            int randomIndexTeacher = new Random().nextInt(teacherList.size());
            Teacher teacher = teacherList.get(randomIndexTeacher);
            int randomTeacherId = teacher.getId();

            int newRandomTeacher = validRandomIdForTeacherOrSubject(randomTeacherId, teachersIds, teacherList);
            Teacher teacher123 = teacherRepository.findById(newRandomTeacher).orElseThrow(() -> new IllegalArgumentException("Not found teacher"));
            schedule.setTeacher(teacher123);
            teachersIds.add(newRandomTeacher);

            int randomClassroom = new Random().nextInt(7) + 1;
            int newRandomClassroom = validRandomNum(randomClassroom, listClassroom, 7);
            Classroom classroom = classroomRepository.findById(newRandomClassroom).orElseThrow(() -> new IllegalArgumentException("Not found classroom"));
            schedule.setClassroom(classroom);
            listClassroom.add(newRandomClassroom);

            Schedule scheduleDB = scheduleRepository.save(schedule);
            scheduleList.add(scheduleDB);
        }
    }

    public int validRandomNumForSubject(int randomId, List<Integer> subjectsIds, List<Subject> subjectList) {
        if (!subjectsIds.contains(randomId)) {
            return randomId;
        } else {
            int randomIndex = new Random().nextInt(subjectList.size());
            randomId = subjectList.get(randomIndex).getId();
            return validRandomNumForSubject(randomId, subjectsIds, subjectList);
        }
    }

    // TODO - come up with a better name please :)
    public int validRandomIdForTeacherOrSubject(int randomId, List<Integer> subjectsIds, List<? extends Domain> teacherList) {
        if (!subjectsIds.contains(randomId)) {
            return randomId;
        } else {
            int randomIndex = new Random().nextInt(teacherList.size());
            randomId = teacherList.get(randomIndex).getId();
            return validRandomIdForTeacherOrSubject(randomId, subjectsIds, teacherList);
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
