package com.example.studentsqlite;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentsqlite.adapter.StudentAdapter;
import com.example.studentsqlite.databinding.ActivityListStudentBinding;
import com.example.studentsqlite.dialog.AddStudentDialog;
import com.example.studentsqlite.dialog.AddStudentInClassDialog;
import com.example.studentsqlite.model.Student;
import com.example.studentsqlite.model.StudentClass;

import java.util.ArrayList;
import java.util.List;

public class ListStudentActivity extends AppCompatActivity {

    private ActivityListStudentBinding binding;
    private StudentAdapter studentAdapter;
    private List<Student> students;
    private AppDatabase appDatabase;
    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityListStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        classId = getIntent().getIntExtra("class detail", -1);
        this.appDatabase = AppDatabase.getInstance(this);
        binding.textAdd.setOnClickListener(v -> {
            if(classId == -1) {
                AddStudentDialog dialog = new AddStudentDialog(ListStudentActivity.this);
                dialog.setCallBackActivity(student -> {
                    student.setStudentId(appDatabase.getAllStudent().size());
                    appDatabase.addStudent(student);
                    ListStudentActivity.this.filterList();
                    studentAdapter.setStudents(students);
                    dialog.dismiss();
                });
                dialog.show();
                dialog.setCancelable(true);
            }else {
                AddStudentInClassDialog dialog = new AddStudentInClassDialog(ListStudentActivity.this , classId);
                dialog.setCallBackActivity(studentClass -> {
                    appDatabase.addStudentClass(studentClass);
                    ListStudentActivity.this.filterList();
                    studentAdapter.setStudents(students);
                    dialog.dismiss();
                });
                dialog.show();
                dialog.setCancelable(true);
            }

        });
        filterList();
        studentAdapter = new StudentAdapter();
        studentAdapter.setStudents(this.students);
        binding.rcvStudents.setAdapter(studentAdapter);
    }

    private void filterList() {
        students = new ArrayList<>();
        List<Student> studentFromDb = new ArrayList<>();
        studentFromDb.addAll(appDatabase.getAllStudent());
        List<StudentClass> studentClasses = new ArrayList<>();
        studentClasses.addAll(appDatabase.getAllStudentClass());

        if (classId == -1) {
            students.addAll(appDatabase.getAllStudent());
        } else {
            for(int i = 0 ; i < studentClasses.size() ; i++) {
                if(studentClasses.get(i).getClassId() == classId) {
                    students.add(getStudentById(studentFromDb , studentClasses.get(i).getStudentId()));
                }
            }
        }
    }

    private Student getStudentById(List<Student> students , int id) {
        for(int i = 0 ; i < students.size() ; i++) {
            if(students.get(i).getStudentId() == id) {
                return students.get(i);
            }
        }
        return null;
    }
}