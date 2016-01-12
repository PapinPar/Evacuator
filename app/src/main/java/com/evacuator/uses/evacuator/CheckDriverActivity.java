package com.evacuator.uses.evacuator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evacuator.uses.evacuator.maps.entity.address.AddressComponent;
import com.evacuator.uses.evacuator.maps.entity.address.Results;
import com.evacuator.uses.evacuator.maps.entity.apies.AddresApi;
import com.evacuator.uses.evacuator.maps.entity.driver.location.Example;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
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
public class CheckDriverActivity extends AppCompatActivity implements OnMapReadyCallback,PlaceSelectionListener {
    public LatLng driverlatlang;
    public LatLng mylatlng;
    private GoogleMap map;
    private MapDrawer drawer;

    private TextView model;
    private TextView brand;
    private TextView address;




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

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        drawer = new MapDrawer(map ,this);
        request();

    }

    @Override
    public void onPlaceSelected(Place place) {

    }

    @Override
    public void onError(Status status) {

    }
    private void request()
    {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app.bb-evacuator.ru/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MyApi api = retrofit.create(MyApi.class);
        String key = "G_DQYrtT";
        Call<Example> result = api.getDriverInfo(key);
        result.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Response<Example> response, Retrofit retrofit) {

                Example result = response.body();
                driverlatlang = new LatLng(result.getGpsLatitude(),result.getGpsLongitude());
                String driverAddress = result.getAddress();
                drawer.addMarker(driverlatlang,driverAddress,R.mipmap.icon_1);
                brand.setText(result.getBrand().getName());
                model.setText(result.getModel().getName());
                //address.setText(result.getAddress());
            }

            public void onFailure(Throwable t) {
                Log.d("SD", "SD2");
                Toast.makeText(getApplicationContext(), "BAD", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
