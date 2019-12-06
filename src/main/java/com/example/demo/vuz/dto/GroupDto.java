package com.example.demo.vuz.dto;

import java.util.List;
import java.util.Objects;

// TODO - a value class like this one, should ALWAYS have hashCode() and equals(), and a toString()
public class GroupDto {

    private String name;
    private List<Integer> studentsIds;
    private List<Integer> groupsIds;
    private int groupId;
    private int departmentId;
    private int maxSize;
    private List<Integer> subjectsIds;

    public GroupDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(List<Integer> studentsIds) {
        this.studentsIds = studentsIds;
    }

    public List<Integer> getGroupsIds() {
        return groupsIds;
    }

    public void setGroupsIds(List<Integer> groupsIds) {
        this.groupsIds = groupsIds;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;

    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Integer> getSubjectsIds() {
        return subjectsIds;
    }

    public void setSubjectsIds(List<Integer> subjectsIds) {
        this.subjectsIds = subjectsIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupDto)) return false;
        GroupDto groupDto = (GroupDto) o;
        return groupId == groupDto.groupId &&
                departmentId == groupDto.departmentId &&
                maxSize == groupDto.maxSize &&
                Objects.equals(name, groupDto.name) &&
                Objects.equals(studentsIds, groupDto.studentsIds) &&
                Objects.equals(groupsIds, groupDto.groupsIds) &&
                Objects.equals(subjectsIds, groupDto.subjectsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentsIds, groupsIds, groupId, departmentId, maxSize, subjectsIds);
    }

    @Override
    public String toString() {
        return "GroupDto{" +
                "name='" + name + '\'' +
                ", studentsIds=" + studentsIds +
                ", groupsIds=" + groupsIds +
                ", groupId=" + groupId +
                ", departmentId=" + departmentId +
                ", maxSize=" + maxSize +
                ", subjectsIds=" + subjectsIds +
                '}';
    }
}
