package com.example.redi.MyFirstAndroidApp.models.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by ReDI on 01/12/2016.
 */

public class BoundService extends Service {

    private final IBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public String getElapsedRealTime() {
        return String.valueOf(SystemClock.elapsedRealtime());
    }

    public class LocalBinder extends Binder {
        public BoundService getBoundService() {
            return BoundService.this;
        }
    }


}
