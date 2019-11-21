package com.example.demo.vuz.model;

import com.example.demo.vuz.services.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "groups")
public class Groups {
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

    private final int MAX_SIZE = 10;
    @Transient
    private final static Logger LOGGER = LoggerFactory.getLogger(GroupService.class.getName());

    public Groups() {
    }

    public Groups(int id, String name, List<Student> studentList) {
        this.id = id;
        this.name = name;
        this.studentList = studentList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void addStudentInGroup(Student student) {
        int count = getStudentList().size();
        //getStudentList().add(new Student());
        if (MAX_SIZE > count) {
            student.setGroup(this);
            count++;
        } /*else {
            LOGGER.error("Limit group is 10 students");
            throw new IllegalArgumentException("Limit group is 10 students");
        }*/
        else {
            try {
                throw new IllegalArgumentException("Limit group is 10000000000000000000000000000000000 students");
            } catch (IllegalArgumentException e) {
                LOGGER.error("Limit group is 100000 students");
            }
        }
    }

    public void addNewStudent(Student student){
        int count = 0;
        if (MAX_SIZE > count) {
            student.setGroup(this);
            count++;
        } else {
            count = 0;
            LOGGER.error("Limit group is 100000000000000000000 students");
            throw new IllegalArgumentException("Limit group is 10 students");
        }
    }






    public List<Student> getStudentList() {
        List<Student> students = List.copyOf(studentList);
        List<Student> unm = Collections.unmodifiableList(students);
        return unm;
    }
    /*public List<Student> getStudentList() {
         return List.copyOf(studentList);
    }*/



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



    /*public void addStudent(Student student){
        students.add(student);
    }*/

    @Override
    public String toString() {
        return "Group: " +
                "name='" + name;
    }
}
