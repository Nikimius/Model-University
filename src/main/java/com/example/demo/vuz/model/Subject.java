package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Subject extends Domain {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Schedule> schedules = new ArrayList<>();

    @ManyToMany(mappedBy = "listSubjects")
    private List<Groups> listGroups = new ArrayList<>();

    @ManyToMany(mappedBy = "subjects")
    private List<Teacher> teacherList = new ArrayList<>();

    public Subject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Groups> getListGroups() {
        return listGroups;
    }

    public void setListGroups(List<Groups> listGroups) {
        this.listGroups = listGroups;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        if (!super.equals(o)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name) &&
                Objects.equals(schedules, subject.schedules) &&
                Objects.equals(listGroups, subject.listGroups) &&
                Objects.equals(teacherList, subject.teacherList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, schedules, listGroups, teacherList);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", schedules=" + schedules +
                ", listGroups=" + listGroups +
                ", teacherList=" + teacherList +
                '}';
    }
}
