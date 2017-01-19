package com.example.redi.MyFirstAndroidApp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.redi.MyFirstAndroidApp.models.Email;

public class Parcelable extends AppCompatActivity {

    public static final String EMAIL_KEY = "EMAIL_KEY";
    private Email email;
    TextView textview1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable);
        SetupUI();


        textview1 = (TextView) findViewById(R.id.parc_txt2);

        if (savedInstanceState != null) {
            email = savedInstanceState.getParcelable(EMAIL_KEY);
            textview1.setText(email.getFrom());
        }


    }

    private void SetupUI() {
        final TextView textview = (TextView) findViewById(R.id.parc_txt1);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Button button = (Button) findViewById(R.id.parc_btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                textview.setText("Button Clicked");
                email = new Email("sbaihi.alaa@gmail.com", "redischool@redi.com");
                textview1.setText(email.getFrom());
                checkBox.setChecked(true);
                imageView.setImageResource(R.drawable.ic_close_dark);
                imageView.setBackgroundColor(0x00000000);
            }
        });

        ImageButton imageButton = (ImageButton) findViewById(R.id.parc_img_btn_home);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parcelable.this, MainActivity.class));
            }
        });


        ImageButton imageButton1 = (ImageButton) findViewById(R.id.parc_img_btn_std);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parcelable.this, RecyclerActivity.class));
            }
        });


        ImageButton imageButton2 = (ImageButton) findViewById(R.id.parc_img_btn_tab);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parcelable.this, TapsShowRecyclerActivity.class));
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.parc_img_btn_ser);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parcelable.this, BoundServiceActivity.class));
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.parc_img_btn_map);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parcelable.this, MyMapsActivity.class));
            }
        });

        ImageButton imageButton5 = (ImageButton) findViewById(R.id.parc_img_btn_exit);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                onDestroy();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EMAIL_KEY, email);

    }
}
