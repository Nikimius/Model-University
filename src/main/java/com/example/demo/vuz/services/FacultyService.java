package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Faculty;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;

    public FacultyService(FacultyRepository facultyRepository, DepartmentRepository departmentRepository) {
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
    }

    public void createFaculty(String name, String webSite, List<Integer> departmentsIds){
        Faculty newFaculty = new Faculty();
        newFaculty.setName(name);
        newFaculty.setWebSite(webSite);

        List<Department> departments = departmentRepository.findAllById(departmentsIds);
        departments.forEach(department -> department.setFaculty(newFaculty));
        newFaculty.setDepartmentList(departments);

        facultyRepository.save(newFaculty);
    }

    public void removeFaculty(List<Integer> facultiesIds){
        List<Faculty> faculties = facultyRepository.findAllById(facultiesIds);
        faculties.forEach(faculty -> removeFc(faculty));
    }

    public void removeFc(Faculty faculty){
        List<Department> departments = faculty.getDepartmentList();
        departments.forEach(department -> department.setFaculty(null));
        facultyRepository.delete(faculty);
    }
}
