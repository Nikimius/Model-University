package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO Entities SHOULD ALSO have hashCode() and equals()...
@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "number_classroom")
    private int numberClassroom;

    @Column(name = "max_size")
    private int maxSize = 10;

    // TODO getter and setter ?
    @OneToMany(mappedBy = "classroom")
    private List<Schedule> scheduleList = new ArrayList<>();

    public Classroom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
