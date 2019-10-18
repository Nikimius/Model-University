package com.example.demo.vuz.model;

public class
Classroom {

    private int number;
    //private int size;


    public Classroom(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Classroom: " +
                "number " + number;
    }
}
