package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Department;
import com.example.demo.vuz.model.Groups;
import com.example.demo.vuz.model.Teacher;
import com.example.demo.vuz.repositories.DepartmentRepository;
import com.example.demo.vuz.repositories.GroupeRepository;
import com.example.demo.vuz.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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


    public void createDep(String name, List<Integer> groupsIds, List<Integer> teachersIds){
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

        departmentRepository.save(newDepartment);
    }

    public void removeDepartment(List<Integer> departmentsIds){
        List<Department> departments = Arrays.asList(departmentRepository.findAllByIdIn(departmentsIds));
        departments.forEach(department -> removeDp(department));
    }

    public void removeDp(Department department){
        List<Groups> groups = department.getGroupList();
        groups.forEach(group -> group.setDepartment(null));
        List<Teacher> teacherList = department.getTeacherList();
        teacherList.forEach(teacher -> teacher.setDepartment(null));
        departmentRepository.delete(department);
    }

    public void addGroupInDepartment(List<Integer> groupsIds, int departmentId) {
        List<Groups> groups = groupeRepository.findAllById(groupsIds);
        Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new IllegalArgumentException("Not found department"));
        groups.forEach(group-> {
            group.setDepartment(department);
            groupeRepository.save(group);
        });
    }

}
