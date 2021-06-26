package com.example.studentsqlite.model;

public class ClassModel {

    private int classId;
    private String name;
    private String desc;

    public ClassModel(int classId, String name, String desc) {
        this.classId = classId;
        this.name = name;
        this.desc = desc;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
