package com.evacuator.uses.evacuator;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.evacuator.uses.evacuator.maps.entity.GeocodedWaypoint;
import com.evacuator.uses.evacuator.maps.entity.GeocodedWaypoints;
import com.evacuator.uses.evacuator.maps.entity.MapApi;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


//home

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,PlaceSelectionListener , View.OnClickListener{

    private GoogleMap map;
    private Button confirmButton;
    private String myAddress;
    private GoogleApiClient mGoogleApiClient;
    private LatLng mylatlng;
    private String myId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        confirmButton = (Button)findViewById(R.id.confirmBut);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.getView().setBackgroundColor(0);
        autocompleteFragment.setOnPlaceSelectedListener(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        initMap();
        Location mylocation = getLocation();
      //  mylatlng = new LatLng(40,30);
        if(mylocation !=null){
            mylatlng = new LatLng(mylocation.getLatitude(),mylocation.getLongitude());
            myAddress = getAddress(mylatlng.latitude,mylatlng.longitude);
        }



        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-27, 133))
                .zoom(15)
                //.bearing(45)
            //    .tilt(20)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
        confirmButton.setText(myAddress);
        addMarker(mylatlng, myAddress);
    }

    public void addMarker(LatLng latlng,String address){
        map.addMarker(new MarkerOptions()
                .position(latlng)
                .title(address));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latlng)
                .zoom(15)
               // .bearing(45)
               // .tilt(20)
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
        map.setMyLocationEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(false);
        map.getUiSettings().setCompassEnabled(false);
        map.getUiSettings().setMapToolbarEnabled(false);
        map.getUiSettings().setRotateGesturesEnabled(false);
    }
    public Location getLocation() {
        // Get the location manager
        LocationManager locationManager = (LocationManager)
                getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Location location = locationManager
                .getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        if(location==null){
            return LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
        }

        return location;
    }

    private String getAddress(double latitude,double longtitude){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longtitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(addresses==null)
            return null;
        else
        //return addresses.get(0).getAddressLine(0);
        return null;

    }

    @Override
    public void onPlaceSelected(Place place) {
        confirmButton.setText(place.getName());
        myAddress = place.getName().toString();
        mylatlng = place.getLatLng();
        myId = place.getId();
        map.clear();
        addMarker(mylatlng, myAddress);
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
        intent.putExtra("latlng",mylatlng);
        intent.putExtra("address",""+myAddress);
        intent.putExtra("id",""+myId);
        startActivity(intent);
    }
}