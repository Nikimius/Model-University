package com.example.demo.vuz.dto;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDto)) return false;
        StudentDto that = (StudentDto) o;
        return groupId == that.groupId &&
                Objects.equals(fn, that.fn) &&
                Objects.equals(ln, that.ln) &&
                Objects.equals(studentsIds, that.studentsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fn, ln, studentsIds, groupId);
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "fn='" + fn + '\'' +
                ", ln='" + ln + '\'' +
                ", studentsIds=" + studentsIds +
                ", groupId=" + groupId +
                '}';
    }
}
