package com.example.redi.MyFirstAndroidApp.models.repository;

import android.support.v7.app.AppCompatActivity;

import com.example.redi.MyFirstAndroidApp.models.ui.fragments.RetainFragment;

/**
 * Created by ReDI on 1/24/2017.
 */

public class Repository extends AppCompatActivity {


    private RetainFragment retainFragment;

/*    public Repository() {
        retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(NETWORK_FRAGMENT_TAG);
        if (retainFragment == null) {
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(retainFragment,NETWORK_FRAGMENT_TAG).commit();
        }

    }*/

    public RetainFragment getRetainFragment() {
        return retainFragment;
    }
}
