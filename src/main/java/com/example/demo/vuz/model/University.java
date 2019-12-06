package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "web_site")
    private String webSite;

    @Column(name = "city")
    private String City;

    @OneToMany(mappedBy = "university")
    private List<Faculty> facultyList;

    public University(int id, String name, String webSite, String city, List<Faculty> faculties) {
        this.id = id;
        this.name = name;
        this.webSite = webSite;
        this.City = city;
        this.facultyList = faculties;

    }

    public University() {
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
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

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "University: " + System.lineSeparator() +
                "name - '" + name + '\'' +
                ", webSite - '" + webSite + '\'' +
                ", City - '" + City + '\'' + System.lineSeparator();
    }
}
