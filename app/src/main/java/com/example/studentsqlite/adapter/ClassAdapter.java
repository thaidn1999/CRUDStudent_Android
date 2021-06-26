package com.example.studentsqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsqlite.ListStudentActivity;
import com.example.studentsqlite.databinding.ItemStudentBinding;
import com.example.studentsqlite.model.ClassModel;
import com.example.studentsqlite.model.Student;
import com.example.studentsqlite.model.StudentClass;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private List<ClassModel> classes;
    private List<StudentClass> students;

    public void setClasses(List<ClassModel> classes , List<StudentClass> students) {
        this.classes = classes;
        this.students = students;
        notifyDataSetChanged();
    }

    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        ItemStudentBinding binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.getContext()) , parent , false) ;
        return new ViewHolder(binding.getRoot() , binding , parent.getContext());
    }

    @Override
    public void onBindViewHolder( ClassAdapter.ViewHolder holder, int position) {
        holder.bindView(classes.get(position));
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemStudentBinding binding;
        private Context context;

        public ViewHolder( View itemView , ItemStudentBinding binding , Context context) {
            super(itemView);
            this.binding = binding;
            this.context = context;
        }

        public void bindView(ClassModel classModel) {
            binding.textName.setText(classModel.getName());
            binding.textId.setText(String.valueOf(classModel.getClassId()));
            int studentInClassNumber = 0;
            for(int i = 0 ; i < students.size() ; i++) {
                if(classModel.getClassId() == students.get(i).getClassId()) {
                    studentInClassNumber ++;
                }
            }
            binding.textDob.setText(String.valueOf(studentInClassNumber) + " Students");
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ListStudentActivity.class);
                intent.putExtra("class detail" , classModel.getClassId());
                context.startActivity(intent);
            });
        }
    }
}
