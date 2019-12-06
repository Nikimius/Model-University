package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final GroupeRepository groupeRepository;
    private final TeacherRepository teacherRepository;

    public DepartmentService(DepartmentRepository departmentRepository, GroupeRepository groupeRepository, TeacherRepository teacherRepository) {
        this.departmentRepository = departmentRepository;
        this.groupeRepository = groupeRepository;
        this.teacherRepository = teacherRepository;
    }

    public Department createDepartment(String name, List<Integer> groupsIds, List<Integer> teachersIds) {
        Department newDepartment = new Department();
        newDepartment.setName(name);
        newDepartment.setNumberTelephone(Math.abs(new Random().nextInt() % 10_00_00_00));

        List<Groups> groups = groupeRepository.findAllById(groupsIds);
        groups.forEach(group -> group.setDepartment(newDepartment));
        newDepartment.setGroupList(groups);
        departmentRepository.save(newDepartment);


        List<Teacher> teachers = teacherRepository.findAllById(teachersIds);
        teachers.forEach(teacher -> teacher.setDepartment(newDepartment));
        newDepartment.setTeacherList(teachers);

        return departmentRepository.save(newDepartment);
    }

    public void deleteDepartmentsByIdIn(List<Integer> departmentsIds) {
        List<Department> departments = departmentRepository.findAllById(departmentsIds);
        departments.forEach(departmentRepository::delete);
        //departments.forEach(department -> deleteDepartment(department));
    }

    /*public void deleteDepartment(Department department) {
        List<Groups> groups = department.getGroupList();
        groups.forEach(group -> group.setDepartment(null));
        List<Teacher> teacherList = department.getTeacherList();
        teacherList.forEach(teacher -> teacher.setDepartment(null));
        departmentRepository.delete(department);
    }*/

    public Department addGroupInDepartment(List<Integer> groupsIds, int id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found department"));
        List<Groups> groups = groupeRepository.findAllById(groupsIds);
        groups.forEach(group -> {
            group.setDepartment(department);
            groupeRepository.save(group);
        });
        return departmentRepository.save(department);
    }
}
