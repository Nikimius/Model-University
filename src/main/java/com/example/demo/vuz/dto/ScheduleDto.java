package com.example.demo.vuz.dto;

import java.util.List;

// TODO - a value class like this one, should ALWAYS have hashCode() and equals(), and a toString()
public class ScheduleDto {

    boolean start;
    private int dayOfWeek;
    private String from;
    private String to;
    private int subject;
    private int classroomId;
    private int groupId;
    private int teacherId;
    private int scheduleId;
    private List<Integer> scheduleIds;

    public ScheduleDto() {
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

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<Integer> getScheduleIds() {
        return scheduleIds;
    }

    public void setScheduleIds(List<Integer> scheduleIds) {
        this.scheduleIds = scheduleIds;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
