package com.example.studentsqlite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsqlite.databinding.ItemStudentBinding;
import com.example.studentsqlite.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemStudentBinding binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding.getRoot() , binding);
    }

    @Override
    public void onBindViewHolder(StudentAdapter.ViewHolder holder, int position) {
        holder.bindView(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemStudentBinding binding;

        public ViewHolder(View itemView , ItemStudentBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        public void bindView(Student student) {
            binding.textName.setText(student.getName());
            binding.textDob.setText(student.getDob());
            binding.textId.setText(String.valueOf(student.getStudentId()));
        }
    }
}
