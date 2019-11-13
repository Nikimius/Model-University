package com.example.demo.vuz.dto;

import java.util.List;

public class UniversityDto {

    private String name;
    private String webSite;
    private String city;
    private List<Integer> facultiesIds;
    private List<Integer> universitiesIds;

    public UniversityDto() {
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
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Integer> getFacultiesIds() {
        return facultiesIds;
    }

    public void setFacultiesIds(List<Integer> facultiesIds) {
        this.facultiesIds = facultiesIds;
    }

    public List<Integer> getUniversitiesIds() {
        return universitiesIds;
    }

    public void setUniversitiesIds(List<Integer> universitiesIds) {
        this.universitiesIds = universitiesIds;
    }
}
