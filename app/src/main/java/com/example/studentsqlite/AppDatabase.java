package com.example.studentsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.studentsqlite.model.ClassModel;
import com.example.studentsqlite.model.Student;
import com.example.studentsqlite.model.StudentClass;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase";
    private static final String TABLE_STUDENT = "studenttable";
    private static final String TABLE_CLASS = "classtable";
    private static final String TABLE_STUDENT_CLASS = "studentclasstable";
    private static final int DATABASE_VERSION = 1;
    private static final String STUDENT_ID = "studentId";
    private static final String CLASS_ID = "classId";
    private static final String NAME = "name";
    private static final String STUDENT_DOB = "dob";
    private static final String STUDENT_HOMETOWN = "homeTown";
    private static final String STUDENT_SCHOOL_YEAR = "schoolYear";
    private static final String CLASS_DESC = "desc";
    private static final String TERM = "term";
    private static final String NUMBER = "number";

    private static AppDatabase INSTANCE = null;

    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new AppDatabase(context);
        }
        return INSTANCE;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStudentTable =
                String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, " +
                                "%s TEXT, %s TEXT, %s INTEGER)", TABLE_STUDENT,
                        STUDENT_ID, NAME, STUDENT_DOB,
                        STUDENT_HOMETOWN, STUDENT_SCHOOL_YEAR);

        String createClassTable =
                String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, " +
                        "%s TEXT)", TABLE_CLASS, CLASS_ID, NAME , CLASS_DESC);

        String createStudentClassTable =
                String.format("CREATE TABLE %s(%s INTEGER, %s INTEGER, " +
                        "%s TEXT, %s INTEGER)" , TABLE_STUDENT_CLASS , STUDENT_ID , CLASS_ID , TERM , NUMBER);
        db.execSQL(createStudentTable);
        db.execSQL(createClassTable);
        db.execSQL(createStudentClassTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropStudentTbl = String.format("DROP TABLE IF EXISTS %s", TABLE_STUDENT);
        String dropClassTbl = String.format("DROP TABLE IF EXISTS %s", TABLE_CLASS);
        String dropStudentClassTbl = String.format("DROP TABLE IF EXISTS %s", TABLE_STUDENT_CLASS);
        db.execSQL(dropStudentTbl);
        db.execSQL(dropClassTbl);
        db.execSQL(dropStudentClassTbl);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_ID, student.getStudentId());
        contentValues.put(NAME, student.getName());
        contentValues.put(STUDENT_DOB, student.getDob());
        contentValues.put(STUDENT_HOMETOWN, student.getHomeTown());
        contentValues.put(STUDENT_SCHOOL_YEAR, student.getSchoolYear());

        db.insert(TABLE_STUDENT, null, contentValues);
        db.close();

    }

    public void addClass(ClassModel classModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLASS_ID, classModel.getClassId());
        contentValues.put(NAME, classModel.getName());
        contentValues.put(CLASS_DESC, classModel.getDesc());

        db.insert(TABLE_CLASS, null, contentValues);
        db.close();

    }

    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Student student = new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getInt(4));
            students.add(student);
            cursor.moveToNext();
        }

        return students;
    }

    public List<ClassModel> getAllClasses() {
        List<ClassModel> classes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CLASS;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            ClassModel classModel = new ClassModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            classes.add(classModel);
            cursor.moveToNext();
        }

        return classes;
    }

    public void addStudentClass(StudentClass studentClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLASS_ID, studentClass.getClassId());
        contentValues.put(STUDENT_ID, studentClass.getStudentId());
        contentValues.put(TERM, studentClass.getTerm());
        contentValues.put(NUMBER, studentClass.getNumber());

        db.insert(TABLE_STUDENT_CLASS, null, contentValues);
        db.close();

    }

    public List<StudentClass> getAllStudentClass() {
        List<StudentClass> studentClasses = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_STUDENT_CLASS;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            StudentClass studentClass = new StudentClass(cursor.getInt(0), cursor.getInt(1), cursor.getString(2) ,
                    cursor.getInt(3));
            studentClasses.add(studentClass);
            cursor.moveToNext();
        }

        return studentClasses;
    }
}
