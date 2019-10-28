package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_Group")
    private String name;

    @OneToMany(mappedBy ="group")
    private List<Student> studentList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;


/*    @ManyToMany(mappedBy = "groupList")
    private List<Teacher> teacherList = new ArrayList<>();*/

    public Group() {
    }

    public Group(int id, String name, List<Student> studentList) {
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

    public List<Student> getStudentList() {
       return studentList;
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


    /*public void addStudent(Student student){
        students.add(student);
    }*/

    @Override
    public String toString() {
        return "Group: " +
                "name='" + name;
    }
}
