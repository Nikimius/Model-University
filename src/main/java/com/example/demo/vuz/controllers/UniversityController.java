package com.example.demo.vuz.controllers;

import com.example.demo.vuz.DemoApplication;
import com.example.demo.vuz.dto.UniversityDto;
import com.example.demo.vuz.model.*;
import com.example.demo.vuz.repositories.UniversityRepository;
import com.example.demo.vuz.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final DemoApplication.InMemoryStorage inMemoryStorage;
    private final UniversityRepository universityRepository;
    private final UniversityService universityService;

    @Autowired
    public UniversityController(DemoApplication.InMemoryStorage inMemoryStorage, UniversityRepository universityRepository,
                                UniversityService universityService) {
        this.inMemoryStorage = inMemoryStorage;
        this.universityRepository = universityRepository;
        this.universityService = universityService;
    }

    @PostMapping()
    public University createUniversities(@RequestBody UniversityDto universityDto) {
        return universityService.createUniversityDto(universityDto.getName(), universityDto.getWebSite(),
                universityDto.getCity(), universityDto.getFacultiesIds());
        //universityService.createUniversityDto(universityDto);
    }

    @PatchMapping("/{id}")
    public University updateUniversityById(@PathVariable int id, @RequestBody Map<String, Object> objUniversity){
        return universityService.updateUniversityById(id, objUniversity);
    }

    @DeleteMapping()
    public void delUniversities(@RequestBody UniversityDto universityDto) {
        universityService.deleteUniversitiesByIdIn(universityDto.getUniversitiesIds());
    }


    @GetMapping()
    public List<University> getUniversity() {
        return universityRepository.findAll();
    }

    @GetMapping("/{universityId}")
    public University getUniversity1(@PathVariable(name = "universityId") int universityId) {
        return inMemoryStorage.getUniversityById(universityId);
    }

    @GetMapping("/{universityId}/faculties")
    public List<Faculty> getListFaculty(@PathVariable(name = "universityId") int universityId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList();
    }

    @GetMapping("/{universityId}/faculties/{facultyId}")
    public Faculty getFaculty(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
                .stream()
                .filter(faculty -> faculty.getId() == facultyId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"));
    }

    @GetMapping("/{universityId}/faculties/{facultyId}/departments")
    public List<Department> getListDepartment(@PathVariable(name = "universityId") int universityId, @PathVariable(name = "facultyId") int facultyId) {
        return inMemoryStorage.getUniversityById(universityId).getFacultyList()
                .stream()
                .filter(faculty -> faculty.getId() == facultyId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"))
                .getDepartmentList();
    }

    @GetMapping("/{universityId}/faculties/{facultyId}/departments/{departmentId}")
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

    @GetMapping("/{universityId}/faculties/{facultyId}/departments/{departmentId}/teachers")
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

    @GetMapping("/{universityId}/faculties/{facultyId}/departments/{departmentId}/teachers/{teacherId}")
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

    @GetMapping("/{universityId}/faculties/{facultyId}/departments/{departmentId}/groups")
    public List<Groups> getListGroup(@PathVariable(name = "universityId") int universityId,
                                     @PathVariable(name = "facultyId") int facultyId,
                                     @PathVariable(name = "departmentId") int departmentId) {
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

    @GetMapping("/{universityId}/faculties/{facultyId}/departments/{departmentId}/groups/{groupId}")
    public Groups getGroup(@PathVariable(name = "universityId") int universityId,
                           @PathVariable(name = "facultyId") int facultyId,
                           @PathVariable(name = "departmentId") int departmentId,
                           @PathVariable(name = "groupId") int groupId) {
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

//    @GetMapping("/{universityId}/faculties/{facultyId}/departments/{departmentId}/groups/{groupId}/students")
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

//    @GetMapping("/{universityId}/faculties/{facultyId}/departments/{departmentId}/groups/{groupId}/students/{studentId}")
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
