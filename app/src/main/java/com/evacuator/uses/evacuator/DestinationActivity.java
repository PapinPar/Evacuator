package com.evacuator.uses.evacuator;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by root on 06.01.16.
 */
public class DestinationActivity extends AppCompatActivity implements OnMapReadyCallback,PlaceSelectionListener, View.OnClickListener {
    public double myLongitude;
    public double myLatitude;
    public String myAddress;
    public String myId;
    public double destLongitude;
    public double destLatitude;
    public String destAddress;
    public String destId;
    private GoogleMap map;

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dest);

        confirmButton = (Button)findViewById(R.id.confirmBut);
        Intent intent  = getIntent();

        myLongitude = Double.parseDouble(intent.getStringExtra("longitude"));
        myLatitude = Double.parseDouble(intent.getStringExtra("latitude"));
        myAddress = intent.getStringExtra("address");
        myId = intent.getStringExtra("id");


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Откуда вас забрать?");

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        if(destLatitude!= 0 && destLatitude!=0 && destAddress!=null ){
            Intent intent = new Intent(this,OrderNewActivity.class);
            intent.putExtra("myLongitude",""+myLongitude);
            intent.putExtra("myLatitude",""+myLatitude);
            intent.putExtra("myAddress",""+myAddress);
            intent.putExtra("myId",""+myId);

            intent.putExtra("destLongitude",""+destLongitude);
            intent.putExtra("destLatitude",""+destLatitude);
            intent.putExtra("destAddress",""+destAddress);
            intent.putExtra("destId",""+destId);

            startActivity(intent);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        initMap();
        map.addMarker(new MarkerOptions()
                .position(new LatLng(myLatitude, myLongitude))
                .title(myAddress));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(myLatitude, myLongitude))
                .zoom(15)
                .bearing(45)
                .tilt(20)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
        addMarker(myLatitude,myLongitude,myAddress);
    }

    @Override
    public void onPlaceSelected(Place place) {
        Place myPlace = place;
        destAddress = place.getName().toString();
        destLongitude = place.getLatLng().longitude;
        destLatitude = place.getLatLng().latitude;
        destId = place.getId();
        map.clear();

        addMarker(myLatitude, myLongitude, myAddress);
        addMarker(destLatitude, destLongitude, destAddress);
    }

    @Override
    public void onError(Status status) {

    }
    public void addMarker(double myLatitude,double myLongitude,String Address){

        map.addMarker(new MarkerOptions()
                .position(new LatLng(myLatitude, myLongitude))
                .title(myAddress));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(myLatitude, myLongitude))
                .zoom(15)
                .bearing(45)
                .tilt(20)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
    }

    public void initMap() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(false);
        map.getUiSettings().setCompassEnabled(false);
        map.getUiSettings().setMapToolbarEnabled(false);
        map.getUiSettings().setRotateGesturesEnabled(false);
    }

}
