package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "teacher_number")
    private int teacherNumber;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "department_id")
    private Department departmentTeacher;



    /*@ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "Subject"
            , joinColumns = @JoinColumn(name = "teacher_id")
            , inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groupList = new ArrayList<>();*/



    public Teacher(int id, String firstName, String lastName, int age, int teacherNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.teacherNumber = teacherNumber;
    }

    public Teacher() {
    }

    public Department getDepartment() {
        return departmentTeacher;
    }

    public void setDepartment(Department departmentTeacher) {
        this.departmentTeacher = departmentTeacher;
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

    public int getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(int teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    @Override
    public String toString() {
        return "Teacher: " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", teacherNumber=" + teacherNumber;
    }
}
