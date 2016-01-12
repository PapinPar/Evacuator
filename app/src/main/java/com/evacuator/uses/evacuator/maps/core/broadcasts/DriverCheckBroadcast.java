package com.evacuator.uses.evacuator.maps.core.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.evacuator.uses.evacuator.DriverCheckActivity;
import com.evacuator.uses.evacuator.MainActivity;
import com.evacuator.uses.evacuator.R;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by root on 12.01.16.
 */
public class DriverCheckBroadcast extends BroadcastReceiver {
    DriverCheckActivity activity;
    public DriverCheckBroadcast(DriverCheckActivity activity){
        this.activity = activity;

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LatLng latLng = intent.getParcelableExtra("latlng");
        String model = intent.getStringExtra("model");
        String brand = intent.getStringExtra("brand");
        String address = intent.getStringExtra("address");
        String status = intent.getStringExtra("status");
        if(status.equals("Завершен"));
            activity.status.setBackgroundColor(Color.RED);
        activity.status.setText(status);
        activity.driverlatlang = latLng;
        activity.drawer.addMarker(latLng,address, R.mipmap.pincar);
        activity.model.setText(model);
        activity.brand.setText(brand);
        activity.address.setText(address);
    }
}
