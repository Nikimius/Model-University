package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO Entities SHOULD ALSO have hashCode() and equals()...
@Entity
public class Teacher extends Domain {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "teacher_number")
    private int teacherNumber;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "department_id")
    private Department departmentTeacher;

    @OneToMany(mappedBy = "teacher")
    private List<Schedule> scheduleList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "teachers_subjects",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects = new ArrayList<>();

    public Teacher(String firstName, String lastName, int age, int teacherNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.teacherNumber = teacherNumber;
    }

    public Teacher() {
    }

    public Department getDepartment() {
        return departmentTeacher;
    }

    public void setDepartment(Department departmentTeacher) {
        this.departmentTeacher = departmentTeacher;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(int teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Department getDepartmentTeacher() {
        return departmentTeacher;
    }

    public void setDepartmentTeacher(Department departmentTeacher) {
        this.departmentTeacher = departmentTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return age == teacher.age &&
                teacherNumber == teacher.teacherNumber &&
                Objects.equals(firstName, teacher.firstName) &&
                Objects.equals(lastName, teacher.lastName) &&
                Objects.equals(departmentTeacher, teacher.departmentTeacher) &&
                Objects.equals(scheduleList, teacher.scheduleList) &&
                Objects.equals(subjects, teacher.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, age, teacherNumber, departmentTeacher, scheduleList, subjects);
    }

    @Override
    public String toString() {
        return "Teacher: " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", teacherNumber=" + teacherNumber;
    }
}
