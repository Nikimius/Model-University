package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.Objects;

// TODO Entities SHOULD ALSO have hashCode() and equals()...
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "student_number")
    private int studentNumber;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group_id")
    private Groups group;

    public Student() {
    }

    public Student(String firstName, String lastName, int age, int studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.studentNumber = studentNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return id == student.id &&
                age == student.age &&
                studentNumber == student.studentNumber &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, studentNumber, group);
    }

    @Override
    public String toString() {
        return "Student: " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", studentNumber=" + studentNumber;
    }
}
