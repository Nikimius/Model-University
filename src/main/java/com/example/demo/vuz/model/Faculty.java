package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO Entities SHOULD ALSO have hashCode() and equals()...
@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "web_site")
    private String webSite;

    @OneToMany(mappedBy = "faculty")
    private List<Department> departmentList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "university_id")
    private University university;

    public Faculty(int id, String name, String webSite, List<Department> departmentList) {
        this.id = id;
        this.name = name;
        this.webSite = webSite;
        this.departmentList = departmentList;
    }

    public Faculty() {
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    @Override
    public String toString() {
        return "name - '" + name + '\'' +
                ", webSite - '" + webSite + '\'' +
                System.lineSeparator() + name + System.lineSeparator();
    }
}
