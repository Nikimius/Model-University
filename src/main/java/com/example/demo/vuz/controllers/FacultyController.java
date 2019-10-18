package com.example.demo.vuz.controllers;


import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Faculty;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/faculties")
public class FacultyController {


    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyController(DemoApplication.InMemoryStorage inMemoryStorage, DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.inMemoryStorage = inMemoryStorage;
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
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
    public void createFaculty(@RequestParam("nameFaculty") String name,
                              @RequestParam("webSite") String webSite,
                              @RequestParam("departmentList") List<Integer> departmentsIds){
        Faculty newFaculty = new Faculty();
        newFaculty.setName(name);
        newFaculty.setWebSite(webSite);

        List<Department> departments = departmentRepository.findAllById(departmentsIds);
        departments.forEach(department -> department.setFaculty(newFaculty));
        newFaculty.setDepartmentList(departments);

        facultyRepository.save(newFaculty);
    }
}