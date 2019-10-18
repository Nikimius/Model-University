package com.example.demo.vuz.model;

import java.util.Calendar;

public class Lecture {

    private Calendar date;
    private Subject subjects;
    private Classroom classrooms;



    public Lecture(Calendar date, Subject subjects, Classroom classrooms) {
        this.date = date;
        this.subjects = subjects;
        this.classrooms = classrooms;
    }


    @Override
    public String toString() {
        return
                "Data is " + date.getTime() + " name subject is " + subjects.getName();
    }
}
