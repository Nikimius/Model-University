package com.example.demo.vuz.dto;

import java.util.List;

// TODO - a value class like this one, should ALWAYS have hashCode() and equals(), and a toString()
public class StudentDto {

    private String fn;
    private String ln;
    private List<Integer> studentsIds;
    private int groupId;

    public StudentDto() {
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public List<Integer> getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(List<Integer> studentsIds) {
        this.studentsIds = studentsIds;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
