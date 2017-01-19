package com.example.redi.MyFirstAndroidApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.redi.MyFirstAndroidApp.models.Student;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private static final int NUM_OF_STUDENTS = 7;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new StudentAdapter(createStudentList(), this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ImageButton imageButton = (ImageButton) findViewById(R.id.std_img_btn_home);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerActivity.this, MainActivity.class));
            }
        });

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.std_img_btn_tab);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerActivity.this, TapsShowRecyclerActivity.class));
            }
        });


        ImageButton imageButton2 = (ImageButton) findViewById(R.id.std_img_btn_ser);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerActivity.this, BoundServiceActivity.class));
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.std_img_btn_lock);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerActivity.this, Parcelable.class));
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.std_img_btn_map);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerActivity.this, MyMapsActivity.class));
            }
        });

        ImageButton imageButton5 = (ImageButton) findViewById(R.id.std_img_btn_exit);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                onDestroy();
            }
        });
    }


    public List<Student> createStudentList() {
        List<Student> students = new ArrayList<>(NUM_OF_STUDENTS);
        students.add(new Student("Alaa", "Male", R.drawable.myfoto11));
        students.add(new Student("Alaa", "Male", R.drawable.myfoto11));
        students.add(new Student("Alaa", "Male", R.drawable.myfoto11));
        students.add(new Student("Alaa", "Male", R.drawable.myfoto111));
        students.add(new Student("Alaa", "Male", R.drawable.myfoto111));

        return students;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
