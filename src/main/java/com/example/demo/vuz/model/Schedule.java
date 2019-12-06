package com.example.demo.vuz.model;

import javax.persistence.*;
import java.util.Objects;

enum DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY
}

/*enum Subject {
    MATHS,
    PHYSICS,
    COMPUTER_SCIENCE,
    HOCKEY,
    FOOTBALL
}*/

@Entity
@Table
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "day_of_week")
    private DayOfWeek day;

    @Column(name = "begin")
    private String from;

    @Column(name = "end")
    private String to;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "subject")
    private Subject subject;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group_id")
    private Groups group;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    public Schedule() {
    }

    public static DayOfWeek transformDayOfWeek(int asd) {
        switch (asd) {
            case 1:
                return DayOfWeek.MONDAY;
            case 2:
                return DayOfWeek.TUESDAY;
            case 3:
                return DayOfWeek.WEDNESDAY;
            case 4:
                return DayOfWeek.THURSDAY;
            case 5:
                return DayOfWeek.FRIDAY;
            case 6:
                return DayOfWeek.SATURDAY;
        }
        // TODO returning null is a bad practice!
        return null;
    }

    /*public static Subject transformSubject(int asd) {
        switch (asd) {
            case 1:
                return Subject.MATHS;
            case 2:
                return Subject.PHYSICS;
            case 3:
                return Subject.COMPUTER_SCIENCE;
            case 4:
                return Subject.HOCKEY;
            case 5:
                return Subject.FOOTBALL;
        }
        return null;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id &&
                day == schedule.day &&
                Objects.equals(from, schedule.from) &&
                Objects.equals(to, schedule.to) &&
                Objects.equals(subject, schedule.subject) &&
                Objects.equals(group, schedule.group) &&
                Objects.equals(teacher, schedule.teacher) &&
                Objects.equals(classroom, schedule.classroom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, day, from, to, subject, group, teacher, classroom);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", day=" + day +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject=" + subject +
                ", group=" + group +
                ", teacher=" + teacher +
                ", classroom=" + classroom +
                '}';
    }
}
