package com.evacuator.uses.evacuator.maps.core.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.evacuator.uses.evacuator.maps.core.threads.AddressThread;

/**
 * Created by root on 12.01.16.
 */
public class AddressService extends Service {
    private double logitude;
    private double latitude;



    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        logitude = intent.getDoubleExtra("lng",logitude);
        latitude = intent.getDoubleExtra("lat",latitude);
        AddressThread runnable = new AddressThread(this,latitude,logitude);
        new Thread(runnable).start();

        return super.onStartCommand(intent, flags, startId);

    }
}
