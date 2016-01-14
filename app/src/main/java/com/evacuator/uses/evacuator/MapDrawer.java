package com.evacuator.uses.evacuator;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.evacuator.uses.evacuator.maps.core.services.AddressService;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11.01.16.
 */
public class MapDrawer {
    GoogleMap map;
    Context context;
    List<MarkerOptions> markers = new ArrayList<>();

    public MapDrawer(GoogleMap map,Context context ){
        this.map = map;
        this.context = context;
    }
    public void addMarker(LatLng latlng, String address,int picRecource) {


        MarkerOptions option = new MarkerOptions()
                            .position(latlng)
                            .title(address);
        if(picRecource!=-111222111) {
            option.icon(BitmapDescriptorFactory.fromResource(picRecource));
        }
        markers.add(option);
        //map.addMarker(option);
       // drawMarker(option);

    }
    public void drawMarker(MarkerOptions option){


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(option.getPosition())
                .zoom(15)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
    }
    public void redrawMarker(LatLng latlng, int id){
        if(markers.size()!=0) {
            MarkerOptions option = markers.get(id);
            if(option.getPosition().longitude !=latlng.longitude && option.getPosition().latitude !=latlng.latitude) {
                option.position(latlng);
                Intent intent = new Intent(context, AddressService.class);
                intent.putExtra("lat", latlng.latitude);
                intent.putExtra("lng", latlng.longitude);
                context.startService(intent);
                map.clear();
              /*  for (MarkerOptions options : markers) {
                    map.addMarker(options);
                }*/
            }
        }
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

    public void bindAddressLast(String address){
        markers.get(markers.size()-1).title(address);
    }
    public void cameraMove(LatLng latlang){


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latlang)
                .zoom(15)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
    }

}
