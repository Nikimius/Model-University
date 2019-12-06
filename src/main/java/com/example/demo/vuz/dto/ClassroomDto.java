package com.example.demo.vuz.dto;

import java.util.List;
import java.util.Objects;

// TODO - a value class like this one, should ALWAYS have hashCode() and equals(), and a toString()
public class ClassroomDto {

    private int numberClassroom;
    private int maxSize;
    private List<Integer> classroomsIds;
    private int classroomId;

    public ClassroomDto() {
    }

    public int getNumberClassroom() {
        return numberClassroom;
    }

    public void setNumberClassroom(int numberClassroom) {
        this.numberClassroom = numberClassroom;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Integer> getClassroomsIds() {
        return classroomsIds;
    }

    public void setClassroomsIds(List<Integer> classroomsIds) {
        this.classroomsIds = classroomsIds;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassroomDto)) return false;
        ClassroomDto that = (ClassroomDto) o;
        return numberClassroom == that.numberClassroom &&
                maxSize == that.maxSize &&
                classroomId == that.classroomId &&
                Objects.equals(classroomsIds, that.classroomsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberClassroom, maxSize, classroomsIds, classroomId);
    }

    @Override
    public String toString() {
        return "ClassroomDto{" +
                "numberClassroom=" + numberClassroom +
                ", maxSize=" + maxSize +
                ", classroomsIds=" + classroomsIds +
                ", classroomId=" + classroomId +
                '}';
    }
}
