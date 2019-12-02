package com.example.demo.vuz.controllers;


import com.example.demo.vuz.dto.DemoDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @PostMapping("/demo")
    public void demoFin(@RequestBody DemoDto demoDto){
        System.out.println("Print day of week " + demoDto.getDayOfWeek());
        System.out.println("Print from " + demoDto.getFrom());
        System.out.println("Print to " + demoDto.getTo());
        System.out.println("Print subject " + demoDto.getSubject());
        System.out.println("Print classroomId " + demoDto.getClassroomId());
        System.out.println("Print groupId " + demoDto.getGroupId());
        System.out.println("Print teacherId " + demoDto.getTeacherId());
    }

    /*@PostMapping("/demo")
    public void demoFin(@RequestBody String str){
        System.out.println(str);
    }*/
}