package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_telephone")
    private int numberTelephone;

    @OneToMany(mappedBy = "department")
    private List<Group> groupList = new ArrayList<>();

    @OneToMany(mappedBy = "departmentTeacher")
    private List<Teacher> teacherList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;


    public Department(int id, String name, int numberTelephone, List<Group> groupList, List<Teacher> teacherList) {
        this.id = id;
        this.name = name;
        this.numberTelephone = numberTelephone;
        this.groupList = groupList;
        this.teacherList = teacherList;
    }

    public Department(){

    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberTelephone() {
        return numberTelephone;
    }

    public void setNumberTelephone(int numberTelephone) {
        this.numberTelephone = numberTelephone;
    }

    @Override
    public String toString() {
        return
                "name - '" + name + '\'' +
                        ", numberTelephone - '" + numberTelephone;
    }
}
