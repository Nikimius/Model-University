package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO Entities SHOULD ALSO have hashCode() and equals()...@Entity
/*@NamedEntityGraph(name = "AnyName.name", attributeNodes = @NamedAttributeNode("name"))*/
@Entity
@Table(name = "department")
public class Department extends Domain{

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


    public Department(String name, int numberTelephone, List<Groups> groupList, List<Teacher> teacherList) {
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

    public Faculty getFaculty() {
        return faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return  numberTelephone == that.numberTelephone &&
                Objects.equals(name, that.name) &&
                Objects.equals(groupList, that.groupList) &&
                Objects.equals(teacherList, that.teacherList) &&
                Objects.equals(faculty, that.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberTelephone, groupList, teacherList, faculty);
    }

    @Override
    public String toString() {
        return "Department{" +
                ", name='" + name + '\'' +
                ", numberTelephone=" + numberTelephone +
                ", groupList=" + groupList +
                ", teacherList=" + teacherList +
                ", faculty=" + faculty +
                '}';
    }
}

