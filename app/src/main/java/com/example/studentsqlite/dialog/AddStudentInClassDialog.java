package com.example.studentsqlite.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.studentsqlite.R;
import com.example.studentsqlite.databinding.DialogAddInClassBinding;
import com.example.studentsqlite.model.StudentClass;

public class AddStudentInClassDialog extends Dialog {

    private DialogAddInClassBinding binding;
    private int classId;

    public AddStudentInClassDialog(@NonNull Context context , int classId) {
        super(context);
        this.classId = classId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogAddInClassBinding.inflate(LayoutInflater.from(getContext()));
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

        binding.etClassId.setText(String.valueOf(classId));
        binding.buttonSave.setOnClickListener(v -> callBackActivity.insertStudentInClass(new StudentClass(Integer.parseInt(binding.etStudentId.getText().toString()),
                Integer.parseInt(binding.etClassId.getText().toString()) , binding.etTerm.getText().toString() ,
                Integer.parseInt(binding.etNumber.getText().toString()))));
    }

    public interface OnCallBackActivity {
        void insertStudentInClass(StudentClass studentClass);
    }

    private OnCallBackActivity callBackActivity;

    public void setCallBackActivity(OnCallBackActivity callBackActivity) {
        this.callBackActivity = callBackActivity;
    }
}
