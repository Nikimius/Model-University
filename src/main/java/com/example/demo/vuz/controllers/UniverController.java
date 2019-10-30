package com.example.demo.vuz.controllers;

import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.model.*;
import com.example.demo.vuz.repositories.FacultyRepository;
import com.example.demo.vuz.repositories.UniversityRepository;
import com.example.demo.vuz.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UniverController {

    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;
    private final UniversityService universityService;


    @Autowired
    public UniverController(DemoApplication.InMemoryStorage inMemoryStorage, UniversityRepository universityRepository, FacultyRepository facultyRepository, UniversityService universityService) {
        this.inMemoryStorage = inMemoryStorage;
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
        this.universityService = universityService;
    }

    @PostMapping("/universities")
    @Transactional
    public void createUniver(@RequestParam("nameUniversity") String name,
                             @RequestParam("webSite") String webSite,
                             @RequestParam("city") String city,
                             @RequestParam("facultyList") List<Integer> facultiesIds) {
        universityService.createUniversity(name, webSite, city, facultiesIds);
    }

    @PostMapping("/delUniversities")
    @Transactional
    public void delUniver(@RequestParam("universityListIds") List<Integer> universitiesIds) {
        universityService.removeUniver(universitiesIds);
    }


    @GetMapping("/universities")
    public List<University> getUniversity() {
        return universityRepository.findAll();
    }

    @GetMapping("/universities/{universityId}")
    public University getUniversity1(@PathVariable(name = "universityId") int universityId) {
        return inMemoryStorage.getUniversityById(universityId);
    }

    @GetMapping("/universities/{universityId}/faculties")
    public List<Faculty> getListFaculty(@PathVariable(name = "universityId") int universityId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList();
    }

    @GetMapping("/universities/{universityId}/faculties/{facultyId}")
    public Faculty getFaculty(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
                .stream()
                .filter(faculty -> faculty.getId() == facultyId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"));
    }

    @GetMapping("/universities/{universityId}/faculties/{facultyId}/departments")
    public List<Department> getListDepartment(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
                .stream()
                .filter(faculty -> faculty.getId() == facultyId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"))
                .getDepartmentList();
    }

    @GetMapping("/universities/{universityId}/faculties/{facultyId}/departments/{departmentId}")
    public Department getDepartment(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId, @PathVariable(name = "departmentId") int departmentId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
                .stream()
                .filter(faculty -> faculty.getId() == facultyId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"))
                .getDepartmentList()
                .stream()
                .filter(department -> department.getId() == departmentId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }

    @GetMapping("/universities/{universityId}/faculties/{facultyId}/departments/{departmentId}/teachers")
    public List<Teacher> getListTeacher(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId, @PathVariable(name = "departmentId") int departmentId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
                .stream()
                .filter(faculty -> faculty.getId() == facultyId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"))
                .getDepartmentList()
                .stream()
                .filter(department -> department.getId() == departmentId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Department not found"))
                .getTeacherList();
    }

    @GetMapping("/universities/{universityId}/faculties/{facultyId}/departments/{departmentId}/teachers/{teacherId}")
    public Teacher getTeacher(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId, @PathVariable(name = "departmentId") int departmentId
            , @PathVariable(name = "teacherId") int teacherId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
                .stream()
                .filter(faculty -> faculty.getId() == facultyId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"))
                .getDepartmentList()
                .stream()
                .filter(department -> department.getId() == departmentId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Department not found"))
                .getTeacherList()
                .stream()
                .filter(teacher -> teacher.getId() == teacherId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
    }

    @GetMapping("/universities/{universityId}/faculties/{facultyId}/departments/{departmentId}/groups")
    public List<Group> getListGroup(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId, @PathVariable(name = "departmentId") int departmentId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
                .stream()
                .filter(faculty -> faculty.getId() == facultyId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"))
                .getDepartmentList()
                .stream()
                .filter(department -> department.getId() == departmentId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Department not found"))
                .getGroupList();
    }

    @GetMapping("/universities/{universityId}/faculties/{facultyId}/departments/{departmentId}/groups/{groupId}")
    public Group getGroup(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId, @PathVariable(name = "departmentId") int departmentId
            , @PathVariable(name = "groupId") int groupId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
                .stream()
                .filter(faculty -> faculty.getId() == facultyId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"))
                .getDepartmentList()
                .stream()
                .filter(department -> department.getId() == departmentId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Department not found"))
                .getGroupList()
                .stream()
                .filter(group -> group.getId() == groupId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));
    }

//    @GetMapping("/universities/{universityId}/faculties/{facultyId}/departments/{departmentId}/groups/{groupId}/students")
//    public List<Student> getListStudent(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId, @PathVariable(name = "departmentId") int departmentId
//            , @PathVariable(name = "groupId") int groupId) {
//        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
//                .stream()
//                .filter(faculty -> faculty.getId() == facultyId)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"))
//                .getDepartmentList()
//                .stream()
//                .filter(department -> department.getId() == departmentId)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Department not found"))
//                .getGroupList()
//                .stream()
//                .filter(group -> group.getId() == groupId)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Group not found"))
//                .getStudentList();
//    }

//    @GetMapping("/universities/{universityId}/faculties/{facultyId}/departments/{departmentId}/groups/{groupId}/students/{studentId}")
//    public Student getStudent(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId, @PathVariable(name = "departmentId") int departmentId
//            , @PathVariable(name = "groupId") int groupId, @PathVariable(name = "studentId") int studentId) {
//        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
//                .stream()
//                .filter(faculty -> faculty.getId() == facultyId)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"))
//                .getDepartmentList()
//                .stream()
//                .filter(department -> department.getId() == departmentId)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Department not found"))
//                .getGroupList()
//                .stream()
//                .filter(group -> group.getId() == groupId)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Group not found"))
//                .getStudentList()
//                .stream()
//                .filter(student -> student.getId() == studentId)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
//    }

}
