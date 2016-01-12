package com.evacuator.uses.evacuator;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evacuator.uses.evacuator.maps.core.broadcasts.DriverCheckBroadcast;
import com.evacuator.uses.evacuator.maps.core.services.DriverCheckService;
import com.evacuator.uses.evacuator.maps.entity.driver.location.Example;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 11.01.16.
 */
public class DriverCheckActivity extends AppCompatActivity implements OnMapReadyCallback,PlaceSelectionListener {
    public LatLng driverlatlang;
    public LatLng mylatlng;
    private GoogleMap map;
    public MapDrawer drawer;
    public Button status;

    public TextView model;
    public TextView brand;
    public TextView address;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_evacuator_check);


        //Intent intent  = getIntent();
        ///mylatlng = intent.getParcelableExtra("mylatlng");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                                                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        model = (TextView)findViewById(R.id.textModel);
        brand = (TextView)findViewById(R.id.textBrand);
        address = (TextView)findViewById(R.id.textAddress);
        status = (Button)findViewById(R.id.status);

        DriverCheckBroadcast broadcast = new DriverCheckBroadcast(this);
        IntentFilter intFilt = new IntentFilter("MY_BROADCAST_CHECK");
        registerReceiver(broadcast, intFilt);

        startService(new Intent(this, DriverCheckService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        drawer = new MapDrawer(map ,this);

    }

    @Override
    public void onPlaceSelected(Place place) {

    }

    @Override
    public void onError(Status status) {

    }
}
