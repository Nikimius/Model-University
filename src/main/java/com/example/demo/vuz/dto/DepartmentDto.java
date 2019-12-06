package com.example.demo.vuz.dto;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentDto)) return false;
        DepartmentDto that = (DepartmentDto) o;
        return departmentId == that.departmentId &&
                Objects.equals(name, that.name) &&
                Objects.equals(groupsIds, that.groupsIds) &&
                Objects.equals(teachersIds, that.teachersIds) &&
                Objects.equals(departmentsIds, that.departmentsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, groupsIds, teachersIds, departmentsIds, departmentId);
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "name='" + name + '\'' +
                ", groupsIds=" + groupsIds +
                ", teachersIds=" + teachersIds +
                ", departmentsIds=" + departmentsIds +
                ", departmentId=" + departmentId +
                '}';
    }
}

