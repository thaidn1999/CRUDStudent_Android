package com.example.studentsqlite.model;

public class StudentClass {

    private int studentId;
    private int classId;
    private String term;
    private int number;

    public StudentClass(int studentId, int classId, String term, int number) {
        this.studentId = studentId;
        this.classId = classId;
        this.term = term;
        this.number = number;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
