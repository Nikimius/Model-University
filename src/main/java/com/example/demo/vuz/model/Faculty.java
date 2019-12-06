package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO Entities SHOULD ALSO have hashCode() and equals()...
@Entity
public class Faculty extends Domain {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "web_site")
    private String webSite;

    @OneToMany(mappedBy = "faculty")
    private List<Department> departmentList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "university_id")
    private University university;

    public Faculty(String name, String webSite, List<Department> departmentList) {
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

    public University getUniversity() {
        return university;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;
        Faculty faculty = (Faculty) o;
        return  Objects.equals(name, faculty.name) &&
                Objects.equals(webSite, faculty.webSite) &&
                Objects.equals(departmentList, faculty.departmentList) &&
                Objects.equals(university, faculty.university);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, webSite, departmentList, university);
    }

    @Override
    public String toString() {
        return "name - '" + name + '\'' +
                ", webSite - '" + webSite + '\'' +
                System.lineSeparator() + name + System.lineSeparator();
    }
}
