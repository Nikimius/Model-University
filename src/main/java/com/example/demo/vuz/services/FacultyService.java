package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Faculty;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;

    public FacultyService(FacultyRepository facultyRepository, DepartmentRepository departmentRepository) {
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
    }

    public Faculty createFaculty(String name, String webSite, List<Integer> departmentsIds) {
        Faculty newFaculty = new Faculty();
        newFaculty.setName(name);
        newFaculty.setWebSite(webSite);

        List<Department> departments = departmentRepository.findAllById(departmentsIds);
        departments.forEach(department -> department.setFaculty(newFaculty));
        newFaculty.setDepartmentList(departments);

        return facultyRepository.save(newFaculty);
    }

    public Faculty updateFaculty(int facultyId, Map<String, Object> dto) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new IllegalArgumentException("Faculty not found"));

        if (dto.containsKey("name")) {
            faculty.setName((String) dto.get("name"));
        }

        if (dto.containsKey("webSite")) {
            faculty.setWebSite((String) dto.get("webSite"));
        }

        if (dto.containsKey("departmentsIds")) {
            @SuppressWarnings("unchecked")
            List<Integer> departmentsIds = (List<Integer>) dto.get("departmentsIds");
            List<Department> departments = departmentRepository.findAllById(departmentsIds);
            departments.forEach(department -> {
                department.setFaculty(faculty);
                departmentRepository.save(department);
            });
        }

            return facultyRepository.save(faculty);
    }

    public void deleteFacultiesByIdIn(List<Integer> facultiesIds) {
        List<Faculty> faculties = facultyRepository.findAllById(facultiesIds);
        faculties.forEach(facultyRepository::delete);
    }
}
