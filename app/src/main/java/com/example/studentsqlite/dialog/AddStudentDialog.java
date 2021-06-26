package com.example.studentsqlite.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

import androidx.annotation.NonNull;

import com.example.studentsqlite.R;
import com.example.studentsqlite.databinding.DialogAddStudentBinding;
import com.example.studentsqlite.model.Student;

public class AddStudentDialog extends Dialog {

    private DialogAddStudentBinding binding;

    public AddStudentDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogAddStudentBinding.inflate(LayoutInflater.from(getContext()));
        setContentView(binding.getRoot());
        Window window = this.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.color.white);
        }
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setLayout(-1, -2);
            window.setGravity(81);
        }

        binding.buttonSave.setOnClickListener(v -> {
            Student student = new Student(0 , binding.etName.getText().toString() , binding.etDob.getText().toString() ,
                    binding.etHomeTown.getText().toString() ,
                    Integer.parseInt(((RadioButton) findViewById(binding.radioGroup.getCheckedRadioButtonId())).getText().toString()));
            callBackActivity.insertStudent(student);
        });
    }

    public interface OnCallBackActivity {
        void insertStudent(Student student);
    }

    private OnCallBackActivity callBackActivity;

    public void setCallBackActivity(OnCallBackActivity callBackActivity) {
        this.callBackActivity = callBackActivity;
    }
}
