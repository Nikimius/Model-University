package com.example.demo.vuz.dto;

import java.util.List;

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
}
