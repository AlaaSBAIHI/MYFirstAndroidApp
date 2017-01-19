package com.example.redi.MyFirstAndroidApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = (ImageButton) findViewById(R.id.main_btn1);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
            }
        });


        ImageButton imageButton1 = (ImageButton) findViewById(R.id.main_btn2);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TapsShowRecyclerActivity.class));
            }
        });


        ImageButton imageButton2 = (ImageButton) findViewById(R.id.main_btn3);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BoundServiceActivity.class));
            }
        });


        ImageButton imageButton3 = (ImageButton) findViewById(R.id.main_btn4);

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Parcelable.class));
            }
        });


        ImageButton imageButton4 = (ImageButton) findViewById(R.id.main_btn5);

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyMapsActivity.class));

            }
        });

        ImageButton imageButton10 = (ImageButton) findViewById(R.id.main_btn10);

        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                onDestroy();
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
