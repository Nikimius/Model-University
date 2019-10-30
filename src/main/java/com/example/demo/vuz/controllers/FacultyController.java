package com.example.demo.vuz.controllers;


import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Faculty;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.FacultyRepository;
import com.example.demo.vuz.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/faculties")
public class FacultyController {


    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(DemoApplication.InMemoryStorage inMemoryStorage, DepartmentRepository departmentRepository, FacultyRepository facultyRepository, FacultyService facultyService) {
        this.inMemoryStorage = inMemoryStorage;
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
        this.facultyService = facultyService;
    }
    @GetMapping("/")
    public List<Faculty> getListFaculty() {
        return facultyRepository.findAll();
    }


    @GetMapping("/{facultyId}")
    public Faculty getFaculty(@PathVariable(name = "facultyId") int facultyId) {
        return inMemoryStorage.getFacultyById(facultyId);
    }


    @GetMapping("/{facultyId}/departments")
    public List<Department> getListDepartment(@PathVariable(name = "facultyId") int facultyId) {
        return inMemoryStorage.getFacultyById(facultyId).getDepartmentList();

    }

    @PostMapping("/faculties")
    @Transactional
    public void createFaculty(@RequestParam("nameFaculty") String name,
                              @RequestParam("webSite") String webSite,
                              @RequestParam("departmentList") List<Integer> departmentsIds){
        facultyService.createFaculty(name, webSite, departmentsIds);
    }

    @PostMapping("/delFaculty")
    @Transactional
    public void delFaculty(@RequestParam("facultyListIds") List<Integer> departmentsIds){
        facultyService.removeFaculty(departmentsIds);
    }
}