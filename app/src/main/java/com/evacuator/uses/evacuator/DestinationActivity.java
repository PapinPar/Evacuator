package com.evacuator.uses.evacuator;


import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.evacuator.uses.evacuator.maps.core.broadcasts.AddressBroadcast;
import com.evacuator.uses.evacuator.maps.core.services.AddressService;
import com.evacuator.uses.evacuator.maps.entity.direction.EndLocation_;
import com.evacuator.uses.evacuator.maps.entity.direction.GeocodedWaypoints;
import com.evacuator.uses.evacuator.maps.entity.apies.MapApi;
import com.evacuator.uses.evacuator.maps.entity.direction.StartLocation_;
import com.evacuator.uses.evacuator.maps.entity.direction.Step;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by root on 06.01.16.
 */
public class DestinationActivity extends AppCompatActivity implements OnMapReadyCallback,PlaceSelectionListener, View.OnClickListener {
    public LatLng destlatlang;
    public LatLng mylatlng;
    public String myAddress;
    public String myId;
    public String destAddress;
    public String destId;
    private GoogleMap map;
    private double pathValue = 0.0;
    private int pathTime = 0;
    public MapDrawer drawer;

    public Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dest);

        confirmButton = (Button)findViewById(R.id.confirmBut);
        confirmButton.setOnClickListener(this);
        Intent intent  = getIntent();


        mylatlng = intent.getParcelableExtra("latlng");
        myAddress = intent.getStringExtra("address");
        myId = intent.getStringExtra("id");


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(this);
        autocompleteFragment.getView().setBackgroundColor(Color.WHITE);

        AddressBroadcast broadcast = new AddressBroadcast(this);
        IntentFilter intFilt = new IntentFilter("MY_BROADCAST_ADDRESS");
        registerReceiver(broadcast, intFilt);

    }


    @Override
    public void onClick(View v) {

        if(destlatlang!=null && destAddress!=null ){
            Intent intent = new Intent(this,OrderNewActivity.class);
            intent.putExtra("mylatlng",mylatlng);
            intent.putExtra("myAddress",""+myAddress);
            intent.putExtra("myId",""+myId);

            intent.putExtra("destlatlng",""+destlatlang);
            intent.putExtra("destAddress",""+destAddress);
            intent.putExtra("destId",""+destId);

            intent.putExtra("path",pathValue/1000);
            intent.putExtra("pathTime",pathTime);

            startActivity(intent);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        drawer = new MapDrawer(map,this);
        initMap();

        drawer.addMarker(mylatlng, myAddress, R.mipmap.pincar);

    }

    @Override
    public void onPlaceSelected(Place place) {
        //destAddress = place.
        destlatlang = place.getLatLng();
        destId = place.getId();
        map.clear();
        drawer.addMarker(mylatlng,myAddress,R.mipmap.pincar);
        drawer.addMarker(destlatlang, destAddress, -111222111);
        confirmButton.setText(destAddress);
        pathValue = 0.0;

        Intent intent = new Intent(this, AddressService.class);
        intent.putExtra("lng", destlatlang.longitude);
        intent.putExtra("lat", destlatlang.latitude);
        startService(intent);

        request();
    }

    @Override
    public void onError(Status status) {

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
        map.getUiSettings().setZoomControlsEnabled(false);
        map.getUiSettings().setCompassEnabled(false);
        map.getUiSettings().setMapToolbarEnabled(false);
        map.getUiSettings().setRotateGesturesEnabled(false);
    }

    private void request()
    {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MapApi api = retrofit.create(MapApi.class);

        String param1 = mylatlng.latitude+","+mylatlng.longitude;
        String param2 = destlatlang.latitude+","+destlatlang.longitude;
        Call<GeocodedWaypoints> usersCall = api.get(param1,param2);
        usersCall.enqueue(new Callback<GeocodedWaypoints>() {
            @Override
            public void onResponse(Response<GeocodedWaypoints> response, Retrofit retrofit) {
                Log.d("SD", "SD");
                GeocodedWaypoints s = response.body();

                ArrayList<LatLng> directionPoint = getDirection(s);
                PolylineOptions rectLine = new PolylineOptions().width(6).color(
                        Color.BLUE);

                for (int i = 0; i < directionPoint.size(); i++) {
                    rectLine.add(directionPoint.get(i));
                }
                Polyline polylin = map.addPolyline(rectLine);


                Toast.makeText(getApplicationContext(), String.valueOf(s.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getDistance().getValue()), Toast.LENGTH_SHORT).show();
            }

            public void onFailure(Throwable t) {
                Log.d("SD", "SD2");
                Toast.makeText(getApplicationContext(), "BAD", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<LatLng> getDirection(GeocodedWaypoints geopoints)
    {
        String path  = geopoints.getRoutes().get(0).getLegs().get(0).getDuration().getText().split(" ")[0];
        pathTime= Integer.parseInt(path);
        List<Step> steps = geopoints.getRoutes().get(0).getLegs().get(0).getSteps();
        NodeList nl1, nl2, nl3;
        ArrayList<LatLng> listGeopoints = new ArrayList<LatLng>();
        for (int i = 0; i < steps.size(); i++) {
            Step step  = steps.get(i);
            StartLocation_ startLocation_ = step.getStartLocation();



            double lat = startLocation_.getLat();
            double lng = startLocation_.getLng();
            listGeopoints.add(new LatLng(lat, lng));
            pathValue+=step.getDistance().getValue();

            com.evacuator.uses.evacuator.maps.entity.direction.Polyline polyline = step.getPolyline();

            ArrayList<LatLng> arr = decodePoly(polyline.getPoints());
            for (int j = 0; j < arr.size(); j++) {
                listGeopoints.add(new LatLng(arr.get(j).latitude, arr
                        .get(j).longitude));
            }
            EndLocation_ endLocation_ = step.getEndLocation();


            lat = endLocation_.getLat();
            lng = endLocation_.getLng();
            listGeopoints.add(new LatLng(lat, lng));
        }

        return listGeopoints;
    }
    private ArrayList<LatLng> decodePoly(String encoded){
        ArrayList<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;
            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng position = new LatLng((double) lat / 1E5, (double) lng / 1E5);
            poly.add(position);
        }
        return poly;
    }
}