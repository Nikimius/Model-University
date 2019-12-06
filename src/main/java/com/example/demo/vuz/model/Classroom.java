package com.example.demo.vuz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO Entities SHOULD ALSO have hashCode() and equals()...
@Entity
public class Classroom extends Domain {

    @Column(name = "number_classroom")
    private int numberClassroom;

    @Column(name = "max_size")
    private int maxSize = 10;

    // TODO getter and setter ?
    @OneToMany(mappedBy = "classroom")
    private List<Schedule> scheduleList = new ArrayList<>();

    public Classroom() {
    }

    public int getNumberClassroom() {
        return numberClassroom;
    }

    public void setNumberClassroom(int numberClassroom) {
        this.numberClassroom = numberClassroom;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Classroom)) return false;
        Classroom classroom = (Classroom) o;
        return numberClassroom == classroom.numberClassroom &&
                maxSize == classroom.maxSize &&
                Objects.equals(scheduleList, classroom.scheduleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberClassroom, maxSize, scheduleList);
    }

    @Override
    public String toString() {
        return "Classroom{" +
                ", numberClassroom=" + numberClassroom +
                ", maxSize=" + maxSize +
                ", scheduleList=" + scheduleList +
                '}';
    }
}
