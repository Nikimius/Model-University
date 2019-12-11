package com.example.demo.vuz.model;

import com.example.demo.vuz.services.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO Entities SHOULD ALSO have hashCode() and equals()...
@Entity
@Table(name = "groups")
public class Groups {
    @Transient
    private final static Logger LOGGER = LoggerFactory.getLogger(GroupService.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public Groups() {
    }

    public Groups(int id, String name, List<Student> studentList) {
        this.id = id;
        this.name = name;
        this.studentList = studentList;
    }

    // TODO maybe returning a boolean would be more useful ?
    public void addStudentInGroup(Student student) {
        int count = getStudentList().size();
        //getStudentList().add(new Student());
        if (maxSize > count) {
            student.setGroup(this);
            // TODO is this needed ? count++ ?
            count++;
        } else {
            LOGGER.info("Limit group students");
        }
    }

    public void addNewStudent(Student student) {
        int count = 0;
        if (maxSize > count) {
            student.setGroup(this);
            count++;
        } else {
            count = 0;
            LOGGER.info("Limit group students");
        }
    }

    /*public List<Student> getStudentList() {
         return List.copyOf(studentList);
    }*/

    public List<Student> getStudentList() {
        List<Student> students = List.copyOf(studentList);
        List<Student> unm = Collections.unmodifiableList(students);
        return unm;
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

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

/*public void addStudent(Student student){
        students.add(student);
    }*/

    @Override
    public String toString() {
        return "Group: " +
                "name='" + name;
    }
}
