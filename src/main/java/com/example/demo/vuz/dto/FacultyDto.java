package com.example.demo.vuz.dto;

import java.util.List;
import java.util.Objects;

// TODO - a value class like this one, should ALWAYS have hashCode() and equals(), and a toString()
public class FacultyDto {

    private String name;
    private String webSite;
    private List<Integer> departmentsIds;
    private List<Integer> facultyListIds;

    public FacultyDto() {
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

    public List<Integer> getDepartmentsIds() {
        return departmentsIds;
    }

    public void setDepartmentsIds(List<Integer> departmentsIds) {
        this.departmentsIds = departmentsIds;
    }

    public List<Integer> getFacultyListIds() {
        return facultyListIds;
    }

    public void setFacultyListIds(List<Integer> facultyListIds) {
        this.facultyListIds = facultyListIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyDto)) return false;
        FacultyDto that = (FacultyDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(webSite, that.webSite) &&
                Objects.equals(departmentsIds, that.departmentsIds) &&
                Objects.equals(facultyListIds, that.facultyListIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, webSite, departmentsIds, facultyListIds);
    }

    @Override
    public String toString() {
        return "FacultyDto{" +
                "name='" + name + '\'' +
                ", webSite='" + webSite + '\'' +
                ", departmentsIds=" + departmentsIds +
                ", facultyListIds=" + facultyListIds +
                '}';
    }
}
