package com.example.demo.vuz.dto;

import java.util.List;
import java.util.Objects;

// TODO - a value class like this one, should ALWAYS have hashCode() and equals(), and a toString()
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

    public int getDepartmentTeacher() {
        return departmentTeacher;
    }

    public void setDepartmentTeacher(int departmentTeacher) {
        this.departmentTeacher = departmentTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherDto)) return false;
        TeacherDto that = (TeacherDto) o;
        return departmentTeacher == that.departmentTeacher &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(teachersIds, that.teachersIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, teachersIds, departmentTeacher);
    }

    @Override
    public String toString() {
        return "TeacherDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", teachersIds=" + teachersIds +
                ", departmentTeacher=" + departmentTeacher +
                '}';
    }
}
