package com.evacuator.uses.evacuator;

import android.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by root on 11.01.16.
 */
public class MapDrawer {
    GoogleMap map;
    Context context;

    public MapDrawer(GoogleMap map,Context context ){
        this.map = map;
        this.context = context;
    }
    public void addMarker(LatLng latlng, String address,int picRecource) {
        if(picRecource<0) {
            map.addMarker(new MarkerOptions()
                    .position(latlng)
                    .title(address))
                    .setIcon(BitmapDescriptorFactory.fromResource(picRecource));
        }
        else{
            map.addMarker(new MarkerOptions()
                    .position(latlng)
                    .title(address));
        }

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latlng)
                .zoom(15)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
    }

    public Location getLocation() {
        // Get the location manager
        LocationManager locationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location==null){
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location ==null) {
                location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                if (location == null) {
                    GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(context)
                            .addApi(LocationServices.API)
                            .build();
                    location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                }
            }
        }

        return location;
    }

}
