package com.example.redi.MyFirstAndroidApp.models.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.redi.MyFirstAndroidApp.R;
import com.example.redi.MyFirstAndroidApp.models.services.BoundService;
import com.example.redi.MyFirstAndroidApp.models.services.BoundService.LocalBinder;

public class BoundServiceActivity extends AppCompatActivity {

    private final String LOG_TAG = getClass().getSimpleName();
    boolean isBound = false;
    private BoundService boundService;
    private Intent intent;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v(LOG_TAG, "Service Connected");
            LocalBinder binder = (LocalBinder) service;
            boundService = binder.getBoundService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);

        Button btnPrintStamp = (Button) findViewById(R.id.btn_stamp);
        Button btnStopService = (Button) findViewById(R.id.btn_stop);
        final TextView txtShowTime = (TextView) findViewById(R.id.txt_elapsed_real_time);


        btnPrintStamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBound) {
                    txtShowTime.setText(boundService.getElapsedRealTime());
                }
            }
        });


        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
            }
        });


        ImageButton imageButton = (ImageButton) findViewById(R.id.ser_img_btn_home);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BoundServiceActivity.this, MainActivity.class));
            }
        });

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.ser_img_btn_std);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BoundServiceActivity.this, RecyclerActivity.class));
            }
        });


        ImageButton imageButton2 = (ImageButton) findViewById(R.id.ser_img_btn_tab);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BoundServiceActivity.this, TapsShowRecyclerActivity.class));
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.ser_img_btn_lock);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BoundServiceActivity.this, Parcelable.class));
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.ser_img_btn_map);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BoundServiceActivity.this, MyMapsActivity.class));
            }
        });

        ImageButton imageButton5 = (ImageButton) findViewById(R.id.ser_img_btn_exit);
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
    protected void onStart() {
        super.onStart();
        intent = new Intent(this, BoundService.class);


        startService(intent);
        Log.v(LOG_TAG, "inStart called Successfully");

        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        Log.v(LOG_TAG, "Service Bound");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (isBound) {
            unbindService(serviceConnection);
            Log.v(LOG_TAG, "Service Unbound");
            isBound = false;
        }
    }

}
