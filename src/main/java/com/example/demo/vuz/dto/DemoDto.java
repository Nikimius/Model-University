package com.example.demo.vuz.dto;

import java.util.Objects;

// TODO - a value class like this one, should ALWAYS have hashCode() and equals(), and a toString()
public class DemoDto {

    private int dayOfWeek;
    private String from;
    private String to;
    private int subject;
    private int classroomId;
    private int groupId;
    private int teacherId;

    public DemoDto() {
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DemoDto)) return false;
        DemoDto demoDto = (DemoDto) o;
        return dayOfWeek == demoDto.dayOfWeek &&
                subject == demoDto.subject &&
                classroomId == demoDto.classroomId &&
                groupId == demoDto.groupId &&
                teacherId == demoDto.teacherId &&
                Objects.equals(from, demoDto.from) &&
                Objects.equals(to, demoDto.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, from, to, subject, classroomId, groupId, teacherId);
    }

    @Override
    public String toString() {
        return "DemoDto{" +
                "dayOfWeek=" + dayOfWeek +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject=" + subject +
                ", classroomId=" + classroomId +
                ", groupId=" + groupId +
                ", teacherId=" + teacherId +
                '}';
    }
}
