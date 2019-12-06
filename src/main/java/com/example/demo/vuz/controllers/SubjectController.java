package com.example.demo.vuz.controllers;

import com.example.demo.vuz.dto.SubjectDto;
import com.example.demo.vuz.model.Subject;
import com.example.demo.vuz.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public Subject createSubject(@RequestBody SubjectDto subjectDto) {
        return subjectService.createSubject(subjectDto.getName());
    }

    @PatchMapping("/{subjectId}")
    public Subject updateSubject(@PathVariable int subjectId, @RequestBody Map<String, Object> subjectDto) {
        return subjectService.updateSubject(subjectId, subjectDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSubjectByIdFrom(@PathVariable int id, @RequestBody Map<String, Integer> subjectDto) {
        subjectService.deleteSubjectByIdIn(id, subjectDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSubjectById(@PathVariable List<Integer> id) {
        subjectService.deleteSubjectById(id);
    }
}
