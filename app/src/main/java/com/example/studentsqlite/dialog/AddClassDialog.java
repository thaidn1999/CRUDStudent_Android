package com.example.studentsqlite.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;

import androidx.annotation.NonNull;

import com.example.studentsqlite.R;
import com.example.studentsqlite.databinding.DialogAddClassBinding;
import com.example.studentsqlite.databinding.DialogAddStudentBinding;
import com.example.studentsqlite.model.ClassModel;
import com.example.studentsqlite.model.Student;

public class AddClassDialog extends Dialog {

    private DialogAddClassBinding binding;

    public AddClassDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogAddClassBinding.inflate(LayoutInflater.from(getContext()));
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
            ClassModel classModel = new ClassModel(0 , binding.etName.getText().toString() , binding.etDesc.getText().toString());
            callBackActivity.insertClass(classModel);
        });
    }

    public interface OnCallBackActivity {
        void insertClass(ClassModel classModel);
    }

    private OnCallBackActivity callBackActivity;

    public void setCallBackActivity(OnCallBackActivity callBackActivity) {
        this.callBackActivity = callBackActivity;
    }
}
