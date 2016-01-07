package com.evacuator.uses.evacuator;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
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

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,PlaceSelectionListener, View.OnClickListener{

    private GoogleMap map;
    private Button confirmButton;
    private Geocoder geocoder;
    private String myAddress;
    private GoogleApiClient mGoogleApiClient;
    private double myLongitude;
    private double myLatitude;
    private Place myPlace;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        confirmButton = (Button)findViewById(R.id.confirmBut);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
        geocoder = new Geocoder(this, Locale.getDefault());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Откуда вас забрать?");
        getSupportActionBar().setTitle("Выбирете ваше положение");

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        initMap();
        Location mylocation = getLocation();
        myLongitude=40;
        myLatitude=39;
        if(mylocation !=null){


            myLongitude =mylocation.getLongitude();
            myLatitude =mylocation.getLatitude();
        }

        // myAddress = getAddress(latitude,longitude);
        myAddress = "hello";
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
        confirmButton.setText(myAddress);
        addMarker();
    }

    public void addMarker(){
        map.clear();
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
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setAllGesturesEnabled(true);
       // map.getUiSettings().setZoomControlsEnabled(false);
        //map.getUiSettings().setCompassEnabled(false);
        //map.getUiSettings().setMapToolbarEnabled(false);
        //map.getUiSettings().setRotateGesturesEnabled(false);
    }
    public Location getLocation() {
        // Get the location manager
        LocationManager locationManager = (LocationManager)
                getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Location location = locationManager
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location==null){
            return LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);


        }

        return location;
    }

    private String getAddress(double latitude,double longtitude){
        geocoder = new Geocoder(this, Locale.getDefault());
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longtitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addresses.get(0).getAddressLine(0);
    }

    public void setLocation(){
        getLocation();

    }



    @Override
    public void onPlaceSelected(Place place) {
        myPlace = place;
        confirmButton.setText(place.getName());
        myAddress = place.getName().toString();
        myLongitude = place.getLatLng().longitude;
        myLatitude = place.getLatLng().latitude;
        addMarker();
    }

    /**
     * Callback invoked when PlaceAutocompleteFragment encounters an error.
     */
    @Override
    public void onError(Status status) {
        Log.e("TAG", "onError: Status = " + status.toString());

        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,DestinationActivity.class);
        intent.putExtra("longitude",""+myPlace.getLatLng().longitude);
        intent.putExtra("latitude",""+myPlace.getLatLng().latitude);
        intent.putExtra("address",""+myPlace.getName());
        intent.putExtra("id",""+myPlace.getId());
        startActivity(intent);
    }
}