package com.example.demo.vuz.controllers;


import com.example.demo.vuz.dto.ScheduleDto;
import com.example.demo.vuz.model.Schedule;
import com.example.demo.vuz.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping()
    public Schedule createSchedule(@RequestBody ScheduleDto scheduleDto) {
        /*return scheduleService.createSchedule(scheduleDto.getDayOfWeek(), scheduleDto.getFrom(), scheduleDto.getTo(), scheduleDto.getGroupId(),
                scheduleDto.getSubject(), scheduleDto.getTeacherId(), scheduleDto.getClassroomId());*/
        return scheduleService.createSchedule(scheduleDto);
    }

    @PatchMapping("/{id}")
    public Schedule changeSchedule(@PathVariable int id, @RequestBody Map<String, String> objDto) {
        return scheduleService.updateSchedule(id, objDto);
    }

    @PostMapping("/generate")
    public List<Schedule> generateNewSchedule() {
        return scheduleService.generateSchedule();
    }

    @DeleteMapping()
    public void deleteSchedule(@RequestBody ScheduleDto scheduleDto) {
        scheduleService.deleteScheduleById(scheduleDto.getScheduleIds());
    }
}
