package com.example.redi.MyFirstAndroidApp.models.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

import com.example.redi.MyFirstAndroidApp.R;
import com.example.redi.MyFirstAndroidApp.models.activities.activitiesAdapters.SettingItemsAdapter;
import com.example.redi.MyFirstAndroidApp.models.entities.SettingItems;

import java.util.ArrayList;
import java.util.List;

public class TapsShowRecyclerActivity extends AppCompatActivity {

    private final int SETTING_ITEMS_NUM = 4;

    //  public static final String SETTING_KEY = "SETTING_KEY";
    // private List<SettingItems> settingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

//        if (savedInstanceState != null) {
//            settingItems = savedInstanceState.getParcelable(SETTING_KEY);
//        }


        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        // TextView x = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);

        ///x.setTextSize(12);

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Device");
        tabSpec.setContent(R.id.recycler_tab1);
        tabSpec.setIndicator("Device");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Languages");
        tabSpec.setContent(R.id.recycler_tab2);
        tabSpec.setIndicator("Languages");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("App Manager");
        tabSpec.setContent(R.id.recycler_tab3);
        tabSpec.setIndicator("App Manager");
        tabHost.addTab(tabSpec);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_tab1);
        recyclerView.setAdapter(new SettingItemsAdapter(getSettingItemses(), this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ImageButton imageButton = (ImageButton) findViewById(R.id.tab_img_btn_home);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TapsShowRecyclerActivity.this, MainActivity.class));
            }
        });


        ImageButton imageButton1 = (ImageButton) findViewById(R.id.tab_img_btn_std);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TapsShowRecyclerActivity.this, RecyclerActivity.class));
            }
        });


        ImageButton imageButton2 = (ImageButton) findViewById(R.id.tab_img_btn_ser);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TapsShowRecyclerActivity.this, BoundServiceActivity.class));
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.tab_img_btn_lock);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TapsShowRecyclerActivity.this, Parcelable.class));
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.tab_img_btn_map);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TapsShowRecyclerActivity.this, MyMapsActivity.class));
            }
        });

        ImageButton imageButton5 = (ImageButton) findViewById(R.id.tab_img_btn_exit);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                onDestroy();
            }
        });
    }

    public List<SettingItems> getSettingItemses() {
        List<SettingItems> settingItemses = new ArrayList<>(SETTING_ITEMS_NUM);
        settingItemses.add(new SettingItems(android.R.drawable.stat_sys_data_bluetooth, "Bluetooth", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_sync, "Synchronization", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_menu_share, "Share", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_reminder, "Reminder", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_reminder, "Reminder", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_reminder, "Reminder", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_reminder, "Reminder", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_reminder, "Reminder", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_reminder, "Reminder", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_reminder, "Reminder", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_reminder, "Reminder", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_reminder, "Reminder", false));
        settingItemses.add(new SettingItems(android.R.drawable.ic_popup_reminder, "Reminder", false));

        return settingItemses;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//       // outState.putParcelableArray(SETTING_KEY, );
//    }
}
