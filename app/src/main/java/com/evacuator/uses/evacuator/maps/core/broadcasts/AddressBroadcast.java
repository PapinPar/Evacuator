package com.evacuator.uses.evacuator.maps.core.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.evacuator.uses.evacuator.DestinationActivity;
import com.evacuator.uses.evacuator.MapDrawer;
import com.evacuator.uses.evacuator.MapsActivity;
import com.evacuator.uses.evacuator.R;
import com.google.android.gms.maps.model.LatLng;


/**
 * Created by root on 12.01.16.
 */
public class AddressBroadcast extends BroadcastReceiver {
    MapsActivity activity = null;
    DestinationActivity destActivity = null;
    public AddressBroadcast(MapsActivity activity){
        this.activity = activity;
    }
    public AddressBroadcast(DestinationActivity activity){
        this.destActivity = activity;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        MapDrawer drawer = null;
        String address = intent.getStringExtra("address");
        LatLng mylatlng = intent.getParcelableExtra("latlng");

       if(activity ==null) {
           destActivity.myAddress = address;
           destActivity.confirmButton.setText(address);
           drawer = destActivity.drawer;
       }
        else{
           activity.myAddress = address;
           activity.confirmButton.setText(address);
           drawer = activity.drawer;
       }

        drawer.addMarker(mylatlng,address, R.mipmap.pincar);
    }
}

