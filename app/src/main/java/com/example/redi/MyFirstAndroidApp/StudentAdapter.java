package com.example.redi.MyFirstAndroidApp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.redi.MyFirstAndroidApp.models.Student;

import java.util.List;

/**
 * Created by ReDI on 29/11/2016.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> students;
    private Context context;

    public StudentAdapter(@NonNull List<Student> students, @NonNull Context context) {
        this.students = students;
        this.context = context;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_item, parent, false);
        return new StudentViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(StudentViewHolder holder, final int position) {
        final Student student = students.get(position);
        holder.studImageTV.setImageResource(student.getImage());
        holder.studNameTV.setText(student.getName());
        holder.studGenderTV.setText(student.getGender());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView studNameTV;
        TextView studGenderTV;
        ImageView studImageTV;


        public StudentViewHolder(View itemView) {
            super(itemView);
            studImageTV = (ImageView) itemView.findViewById(R.id.stud_img);
            studNameTV = (TextView) itemView.findViewById(R.id.stud_name);
            studGenderTV = (TextView) itemView.findViewById(R.id.stud_gen);
        }

    }

}
