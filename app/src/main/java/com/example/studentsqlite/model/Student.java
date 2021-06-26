package com.example.studentsqlite.model;

public class Student {

    private int studentId;
    private String name;
    private String dob;
    private String homeTown;
    private int schoolYear;

    public Student(int studentId, String name, String dob, String homeTown, int schoolYear) {
        this.studentId = studentId;
        this.name = name;
        this.dob = dob;
        this.homeTown = homeTown;
        this.schoolYear = schoolYear;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }
}
