package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
/*@NamedEntityGraph(name = "AnyName.name", attributeNodes = @NamedAttributeNode("name"))*/
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_telephone")
    private int numberTelephone;

    /*@LazyCollection(LazyCollectionOption.FALSE)*/
    @OneToMany(mappedBy = "department")
    private List<Groups> groupList = new ArrayList<>();

    /*@LazyCollection (LazyCollectionOption.FALSE)*/
    @OneToMany(mappedBy = "departmentTeacher")
    private List<Teacher> teacherList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;


    public Department(int id, String name, int numberTelephone, List<Groups> groupList, List<Teacher> teacherList) {
        this.id = id;
        this.name = name;
        this.numberTelephone = numberTelephone;
        this.groupList = groupList;
        this.teacherList = teacherList;
    }

    public Department() {
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Groups> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Groups> groupList) {
        this.groupList = groupList;
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
