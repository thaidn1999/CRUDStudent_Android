package com.example.studentsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.studentsqlite.adapter.ClassAdapter;
import com.example.studentsqlite.databinding.ActivityListClassBinding;
import com.example.studentsqlite.dialog.AddClassDialog;
import com.example.studentsqlite.model.ClassModel;
import com.example.studentsqlite.model.Student;
import com.example.studentsqlite.model.StudentClass;

import java.util.ArrayList;
import java.util.List;

public class ListClassActivity extends AppCompatActivity {

    private ActivityListClassBinding binding;
    private ClassAdapter classAdapter;
    private List<ClassModel> classes;
    private AppDatabase appDatabase;
    private List<StudentClass> studentClasses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appDatabase = AppDatabase.getInstance(this);
        this.studentClasses = new ArrayList<>();
        classAdapter = new ClassAdapter();

        binding.textAdd.setOnClickListener(v -> {
            AddClassDialog dialog = new AddClassDialog(ListClassActivity.this);
            dialog.setCancelable(true);
            dialog.setCallBackActivity(classModel -> {
                classModel.setClassId(classes.size());
                appDatabase.addClass(classModel);
                classes.clear();
                classes.addAll(appDatabase.getAllClasses());
                classAdapter.setClasses(classes , studentClasses);
                dialog.dismiss();
            });
            dialog.show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        classes = new ArrayList<>();
        studentClasses = new ArrayList<>();
        classes.addAll(appDatabase.getAllClasses());
        studentClasses.addAll(appDatabase.getAllStudentClass());
        classAdapter.setClasses(this.classes , studentClasses);
        binding.rcvClass.setAdapter(classAdapter);
    }
}