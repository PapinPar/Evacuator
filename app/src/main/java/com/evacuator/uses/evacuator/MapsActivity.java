package com.evacuator.uses.evacuator;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evacuator.uses.evacuator.maps.core.broadcasts.AddressBroadcast;
import com.evacuator.uses.evacuator.maps.core.broadcasts.DriverCheckBroadcast;
import com.evacuator.uses.evacuator.maps.core.services.AddressService;
import com.evacuator.uses.evacuator.maps.core.services.DriverCheckService;
import com.evacuator.uses.evacuator.maps.entity.address.AddressComponent;
import com.evacuator.uses.evacuator.maps.entity.address.Results;
import com.evacuator.uses.evacuator.maps.entity.apies.AddresApi;
import com.evacuator.uses.evacuator.maps.entity.apies.MapApi;
import com.evacuator.uses.evacuator.maps.entity.direction.GeocodedWaypoints;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


//home

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, PlaceSelectionListener, View.OnClickListener ,GoogleMap.OnCameraChangeListener{

    private GoogleMap map;
    public Button confirmButton;
    public String myAddress;
    private GoogleApiClient mGoogleApiClient;
    private LatLng mylatlng;
    private String myId;
    private final int MY_PERMISSIONS_REQUEST = 21;
    private boolean pirmission_granted = false;
    public MapDrawer drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPirmission();
        setContentView(R.layout.activity_maps);
        confirmButton = (Button) findViewById(R.id.confirmBut);

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
        autocompleteFragment.getView().setBackgroundColor(Color.WHITE);

        AddressBroadcast broadcast = new AddressBroadcast(this);
        IntentFilter intFilt = new IntentFilter("MY_BROADCAST_ADDRESS");
        registerReceiver(broadcast, intFilt);
    }

    private void checkPirmission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            pirmission_granted = true;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnCameraChangeListener(this);
        drawer = new MapDrawer(map,this);
        initMap();
        if (pirmission_granted == true){
            drawMyLocation();
        }
    }

    public void drawMyLocation() {
        Location mylocation = getLocation();

        if (mylocation != null) {
            mylatlng = new LatLng(mylocation.getLatitude(), mylocation.getLongitude());
            Intent intent = new Intent(this, AddressService.class);
            intent.putExtra("lng",mylocation.getLongitude());
            intent.putExtra("lat",mylocation.getLatitude());
            startService(intent);
            drawer.addMarker(mylatlng, "", R.mipmap.pincar);
        }
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location==null){
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location ==null) {
                location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                if (location == null)
                    location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            }
        }

        return location;
    }



    @Override
    public void onPlaceSelected(Place place) {
        confirmButton.setText(place.getName());
        myAddress = place.getName().toString();
        mylatlng = place.getLatLng();
        myId = place.getId();
        map.clear();
        Intent intent = new Intent(this, AddressService.class);
        intent.putExtra("lng",mylatlng.longitude);
        intent.putExtra("lat",mylatlng.latitude);
        startService(intent);
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
    public void onClick(View v)
    {
        Intent getRes = new Intent();
        getRes.putExtra("latlng",mylatlng);
        getRes.putExtra("address",""+myAddress);
        getRes.putExtra("id", "" + myId);
        setResult(RESULT_OK,getRes);
        finish();
       /* Intent intent = new Intent(this,DestinationActivity.class);
        intent.putExtra("latlng",mylatlng);
        intent.putExtra("address",""+myAddress);
        intent.putExtra("id", "" + myId);
        startActivity(intent);*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("permission","get permission");
                    pirmission_granted = true;
                    drawMyLocation();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        drawer.redrawMarker(cameraPosition.target,0);
        mylatlng=cameraPosition.target;
        Log.d("cameara",cameraPosition.target.toString());
    }
}