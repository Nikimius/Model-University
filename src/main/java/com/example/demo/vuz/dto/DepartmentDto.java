package com.example.demo.vuz.dto;

import java.util.List;

// TODO - a value class like this one, should ALWAYS have hashCode() and equals(), and a toString()
public class DepartmentDto {

    private String name;
    private List<Integer> groupsIds;
    private List<Integer> teachersIds;
    private List<Integer> departmentsIds;
    private int departmentId;


    public DepartmentDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGroupsIds() {
        return groupsIds;
    }

    public void setGroupsIds(List<Integer> groupsIds) {
        this.groupsIds = groupsIds;
    }

    public List<Integer> getTeachersIds() {
        return teachersIds;
    }

    public void setTeachersIds(List<Integer> teachersIds) {
        this.teachersIds = teachersIds;
    }

    public List<Integer> getDepartmentsIds() {
        return departmentsIds;
    }

    public void setDepartmentsIds(List<Integer> departmentsIds) {
        this.departmentsIds = departmentsIds;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}

