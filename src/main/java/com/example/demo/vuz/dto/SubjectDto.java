package com.example.demo.vuz.dto;

import java.util.List;

public class SubjectDto {

    private String name;
    private List<Integer> groupsIds;
    private int subjectId;

    public SubjectDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGroupsIds() {
        return groupsIds;
    }

    public void setGroupsIds(List<Integer> groupsIds) {
        this.groupsIds = groupsIds;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
