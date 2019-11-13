package com.example.demo.vuz.dto;

import java.util.List;


public class TeacherDto {

    private String firstName;
    private String lastName;
    private List<Integer> teachersIds;
    private int departmentTeacher;

    public TeacherDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Integer> getTeachersIds() {
        return teachersIds;
    }

    public void setTeachersIds(List<Integer> teachersIds) {
        this.teachersIds = teachersIds;
    }

    public int getDepartment() {
        return departmentTeacher;
    }

    public void setDepartment(int departmentTeacher) {
        this.departmentTeacher = departmentTeacher;
    }
}
