package com.example.studentsqlite;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.studentsqlite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.binding.buttonStudents.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListStudentActivity.class)));
        this.binding.buttonClasses.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListClassActivity.class)));
    }
}