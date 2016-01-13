package com.evacuator.uses.evacuator.maps.core.threads;

import android.app.Service;
import android.content.Intent;
import android.util.Log;

import com.evacuator.uses.evacuator.maps.entity.address.AddressComponent;
import com.evacuator.uses.evacuator.maps.entity.address.Results;
import com.evacuator.uses.evacuator.maps.entity.apies.AddresApi;

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
 * Created by root on 12.01.16.
 */
public class AddressThread implements Runnable {
    private Intent intent;
    private Service service;
    private double latitude;
    private double longitude;


    public AddressThread(Service service,double latitude,double longitude){
        this.intent = new Intent("MY_BROADCAST_ADDRESS");
        this.service = service;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public void run() {
        request();
        try {
            new Thread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.stopSelf();
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

        AddresApi api = retrofit.create(AddresApi.class);

        String param1 = latitude+","+longitude;
        Call<Results> usersCall = api.get(param1);
        usersCall.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Response<Results> response, Retrofit retrofit) {

                Results results = response.body();
                List<AddressComponent> component = results.getResults().get(0).getAddressComponents();
                String address = component.get(3).getLongName() + " ," + component.get(1).getLongName() + "," + component.get(0).getLongName();
                intent.putExtra("address", address);
                intent.putExtra("latlng",new LatLng(latitude,longitude));
                service.sendBroadcast(intent);
            }

            public void onFailure(Throwable t) {
                Log.d("SD", "SD2");
            }
        });
    }
    private String customizeText(String text){
        String[] array = text.split(",");

        return array[0];
    }
}
