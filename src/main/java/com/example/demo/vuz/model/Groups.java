package com.example.demo.vuz.model;

import com.example.demo.vuz.services.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// TODO Entities SHOULD ALSO have hashCode() and equals()...
@Entity
@Table(name = "groups")
public class Groups extends Domain{
    @Transient
    private final static Logger LOGGER = LoggerFactory.getLogger(GroupService.class.getName());

    @Transient
    private final static java.util.logging.Logger log = java.util.logging.Logger.getLogger(Groups.class.getName());

    @Column(name = "name_Group")
    private String name;

    @OneToMany(/*orphanRemoval = true,*/ mappedBy = "group")
    private List<Student> studentList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "department_id")
    private Department department;

    private int maxSize = 10;

    @OneToMany(mappedBy = "group")
    private List<Schedule> schedules = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "groups_subjects",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> listSubjects = new ArrayList<>();


    public Groups() {
    }

    public Groups(String name, List<Student> studentList) {
        this.name = name;
        this.studentList = studentList;
    }

    // TODO maybe returning a boolean would be more useful ?
    public void addStudentInGroup(Student student) {
        int count = getStudentList().size();

        if (maxSize > count) {
            student.setGroup(this);
            this.studentList.add(student);
            //return true;
        } else {
            LOGGER.info("Limit group students");
            log.info("Limit group students(log)");
            //return false;
        }
    }

    public void addNewStudent(Student student) {
        int count = 0;
        if (maxSize > count) {
            student.setGroup(this);
        } else {
            LOGGER.info("Limit group students");
            log.info("Limit group students(log)");
        }
    }

    public List<Student> getStudentList() {
        List<Student> students = List.copyOf(studentList);
        return Collections.unmodifiableList(students);
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Subject> getListSubjects() {
        return listSubjects;
    }

    public void setListSubjects(List<Subject> listSubjects) {
        this.listSubjects = listSubjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Groups)) return false;
        Groups groups = (Groups) o;
        return maxSize == groups.maxSize &&
                Objects.equals(name, groups.name) &&
                Objects.equals(studentList, groups.studentList) &&
                Objects.equals(department, groups.department) &&
                Objects.equals(schedules, groups.schedules) &&
                Objects.equals(listSubjects, groups.listSubjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentList, department, maxSize, schedules, listSubjects);
    }

    @Override
    public String toString() {
        return "Group: " +
                "name='" + name;
    }
}
