package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

// TODO Entities SHOULD ALSO have hashCode() and equals()...Abra Kidabra!
@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // TODO it should be unique
    @Column(name = "name", unique = true)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof University)) return false;
        University that = (University) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(webSite, that.webSite) &&
                Objects.equals(City, that.City) &&
                Objects.equals(facultyList, that.facultyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, webSite, City, facultyList);
    }

    @Override
    public String toString() {
        return "University: " + System.lineSeparator() +
                "name - '" + name + '\'' +
                ", webSite - '" + webSite + '\'' +
                ", City - '" + City + '\'' + System.lineSeparator();
    }
}
