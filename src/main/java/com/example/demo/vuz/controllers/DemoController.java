package com.example.demo.vuz.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    /*@PostMapping("/demo")
    public void demoFin(@RequestBody DemoDto demoDto){
        System.out.println(demoDto.getName());
    }*/

    @PostMapping("/demo")
    public void demoFin(@RequestBody String str){
        System.out.println(str);
    }
}