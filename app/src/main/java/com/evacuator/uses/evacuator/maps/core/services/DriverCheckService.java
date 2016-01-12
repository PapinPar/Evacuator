package com.evacuator.uses.evacuator.maps.core.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Telephony;
import android.support.annotation.Nullable;

import com.evacuator.uses.evacuator.maps.core.broadcasts.DriverCheckBroadcast;
import com.evacuator.uses.evacuator.maps.core.threads.DriverCheckThread;

/**
 * Created by root on 12.01.16.
 */
public class DriverCheckService extends Service {
    private Intent intent;

    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent(new Intent(this,DriverCheckBroadcast.class));



    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String key =intent.getParcelableExtra("key");
        DriverCheckThread runnable = new DriverCheckThread(this,this,"G_DQYrtT",5000);
        new Thread(runnable).start();

        return super.onStartCommand(intent, flags, startId);

    }


}
