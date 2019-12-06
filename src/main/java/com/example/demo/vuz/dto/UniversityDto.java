package com.example.demo.vuz.dto;

import java.util.List;
import java.util.Objects;

// TODO - a value class like this one, should ALWAYS have hashCode() and equals(), and a toString()
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UniversityDto)) return false;
        UniversityDto that = (UniversityDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(webSite, that.webSite) &&
                Objects.equals(city, that.city) &&
                Objects.equals(facultiesIds, that.facultiesIds) &&
                Objects.equals(universitiesIds, that.universitiesIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, webSite, city, facultiesIds, universitiesIds);
    }

    @Override
    public String toString() {
        return "UniversityDto{" +
                "name='" + name + '\'' +
                ", webSite='" + webSite + '\'' +
                ", city='" + city + '\'' +
                ", facultiesIds=" + facultiesIds +
                ", universitiesIds=" + universitiesIds +
                '}';
    }
}
