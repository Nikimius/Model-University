package com.example.demo.vuz;

import com.example.demo.vuz.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, "--debug");
    }

    // mocking
    @Component
    public class InMemoryStorage implements CommandLineRunner {

        public List<University> universityList = new ArrayList<>();
        public List<Faculty> facultyList = new ArrayList<>();
        public List<Department> departmentList = new ArrayList<>();
        public List<Groups> groupList = new ArrayList<>();
        public List<Teacher> teacherList = new ArrayList<>();
        public List<Student> studentList = new ArrayList<>();

        public University getUniversityById(int id) {
            return universityList
                    .stream()
                    .filter(university -> university.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("University not found"));
        }

        public Faculty getFacultyById(int id) {
            return facultyList
                    .stream()
                    .filter(faculty -> faculty.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("Faculty not found"));
        }

        public Department getDepartmentById(int id) {
            return departmentList
                    .stream()
                    .filter(department -> department.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("Department not found"));
        }

        public Groups getGroupById(int id) {
            return groupList
                    .stream()
                    .filter(group -> group.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("Group not found"));
        }

        public Teacher getTeacherById(int id) {
            return teacherList
                    .stream()
                    .filter(teacher -> teacher.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
        }

        public Student getStudentById(int id) {
            return studentList
                    .stream()
                    .filter(student -> student.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("Student not found"));
        }


        void initUniversityStorage() {

            universityList.add(new University(1, 9 + "name", 9 + ".com", "24", facultyList.subList(0, 3)));
            universityList.add(new University(2, 24 + "name", 24 + ".com", "24", facultyList.subList(3, 6)));

        }

        void initFacultyStorage() {
            facultyList.add(new Faculty(1, 1 * 24 + "name" + 1, 1 * 24 + "facul/" + 24 + ".com", departmentList.subList(0,2)));
            facultyList.add(new Faculty(2, 2 * 24 + "name" + 1, 2 * 24 + "facul/" + 24 + ".com", departmentList.subList(2,4)));
            facultyList.add(new Faculty(3, 3 * 24 + "name" + 1, 3 * 24 + "facul/" + 24 + ".com", departmentList.subList(4,6)));
            facultyList.add(new Faculty(4, 4 * 24 + "name" + 1, 4 * 24 + "facul/" + 24 + ".com", departmentList.subList(6,8)));
            facultyList.add(new Faculty(5, 5 * 24 + "name" + 1, 5 * 24 + "facul/" + 24 + ".com", departmentList.subList(8,10)));
            facultyList.add(new Faculty(6, 6 * 24 + "name" + 1, 6 * 24 + "facul/" + 24 + ".com", departmentList.subList(10,12)));

            /*for (int i = 1; i <= 12; i=i+2) {
				facultyList.add(new Faculty(i, i * 24 + "name" + 1, i * 24 + "facul/" + 24 + ".com", departmentList.subList(i-1,i+1)));
			}*/

        }

        void initDepartmentStorage() {
                /*departmentList.add(new Department(1, 1 + "name" + 24, 1 + "245" + 1 * 24, groupList));
                departmentList.add(new Department(2, 2 + "name" + 24, i + "245" + i * 24, groupList));
                departmentList.add(new Department(3, 3 + "name" + 24, i + "245" + i * 24, groupList));
                departmentList.add(new Department(4, 4 + "name" + 24, i + "245" + i * 24, groupList));
                departmentList.add(new Department(5, 5 + "name" + 24, i + "245" + i * 24, groupList));
                departmentList.add(new Department(6, 6 + "name" + 24, i + "245" + i * 24, groupList));
                departmentList.add(new Department(7, 7 + "name" + 24, i + "245" + i * 24, groupList));
                departmentList.add(new Department(8, 8 + "name" + 24, i + "245" + i * 24, groupList));
                departmentList.add(new Department(9, 9 + "name" + 24, i + "245" + i * 24, groupList));
                departmentList.add(new Department(10, 10 + "name" + 24, i + "245" + i * 24, groupList));
                departmentList.add(new Department(11, 11 + "name" + 24, i + "245" + i * 24, groupList));
                departmentList.add(new Department(12, 12 + "name" + 24, i + "245" + i * 24, groupList));*/
			for (int i = 1; i <= 24; i=i+2) {
				departmentList.add(new Department(i, i + "name" + 24, Math.abs(new Random().nextInt() % 10000000), groupList.subList(i-1, i+1), teacherList.subList(i-1, i+2)));
			}
        }

        void initGroupStorage() {
            for (int i = 1; i <= 240; i=i+10) {
                groupList.add(new Groups(i, 13 + "-IST-" + i, studentList.subList(i-1, i+9)));
            }
        }

        void initTeacherStorage() {
            for (int i = 1; i <= 36; i++) {
                teacherList.add(new Teacher(i, "Sergey" + i + "-st/nd/rd/th", "Savinov", 23 + i, i * 24 ^ 9));
            }
        }

        void initStudentStorage() {
            for (int i = 1; i <= 240; i++) {
                studentList.add(new Student( "Sergey" + i, "Savinov", 23 + i, i * 24 ^ 9));
            }
        }

        @Override
        public void run(String... args) throws Exception {
            initStudentStorage();
            initTeacherStorage();
            initGroupStorage();
            initDepartmentStorage();
            initFacultyStorage();
            initUniversityStorage();
        }
    }
}
