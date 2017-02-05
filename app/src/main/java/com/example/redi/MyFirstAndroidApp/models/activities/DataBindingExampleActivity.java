package com.example.redi.MyFirstAndroidApp.models.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.redi.MyFirstAndroidApp.R;
import com.example.redi.MyFirstAndroidApp.databinding.ActivityDataBindingExampleBinding;
import com.example.redi.MyFirstAndroidApp.models.entities.MyUser;
import com.example.redi.MyFirstAndroidApp.models.entities.Venue;
import com.example.redi.MyFirstAndroidApp.models.viewModels.viewModels;

public class DataBindingExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingExampleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_example);

        binding.setViewModels(new viewModels(new Venue("CSC", "Free WIFI", "CoWorking Space", "Unter die Linden, Berlin", 52.0, 13.0), new MyUser("2", "Alaa", "sbaihialaa@gmail.com")));
    }
}
