package com.example.demo.vuz.controllers;


import com.example.demo.vuz.dto.ScheduleDto;
import com.example.demo.vuz.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {


    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public void crateSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.createSchedule(scheduleDto.getDayOfWeek(), scheduleDto.getFrom(), scheduleDto.getTo(), scheduleDto.getGroupId(),
                scheduleDto.getSubject(), scheduleDto.getTeacherId(), scheduleDto.getClassroomId());
    }

    @PatchMapping("/schedule/dayOfWeek")
    public void changeDayOfWeekSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.changeDayOfWeek(scheduleDto.getScheduleId(), scheduleDto.getDayOfWeek());
    }

    @PatchMapping("/schedule/from")
    public void changeFromSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.changeFrom(scheduleDto.getScheduleId(), scheduleDto.getFrom());
    }

    @PatchMapping("/schedule/to")
    public void changeToSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.changeTo(scheduleDto.getScheduleId(), scheduleDto.getTo());
    }

    @PatchMapping("/schedule/group")
    public void changeGroupSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.changeGroupId(scheduleDto.getScheduleId(), scheduleDto.getGroupId());
    }

    @PatchMapping("/schedule/subject")
    public void changeSubjectSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.changeSubject(scheduleDto.getScheduleId(), scheduleDto.getSubject());
    }

    @PatchMapping("/schedule/teacher")
    public void changeTeacherSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.changeTeacherId(scheduleDto.getScheduleId(), scheduleDto.getTeacherId());
    }

    @PatchMapping("/schedule/classroom")
    public void changeClassroomSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.changeClassroomId(scheduleDto.getScheduleId(), scheduleDto.getClassroomId());
    }



}