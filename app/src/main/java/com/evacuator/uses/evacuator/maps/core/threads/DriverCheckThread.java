package com.evacuator.uses.evacuator.maps.core.threads;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.evacuator.uses.evacuator.Entity.MyApi;
import com.evacuator.uses.evacuator.R;
import com.evacuator.uses.evacuator.maps.core.broadcasts.DriverCheckBroadcast;
import com.evacuator.uses.evacuator.maps.entity.driver.location.Example;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 12.01.16.
 */
public class DriverCheckThread implements Runnable {

    private Intent intent;
    private String key;
    private String status="В пути";
    private long time;
    private Service service;


    public DriverCheckThread(Service service,String key, int time){
        this.intent = new Intent("MY_BROADCAST_CHECK");
        this.key = key;
        this.time = time;
        this.service = service;

    }

    @Override
    public void run() {
        while(!status.equals("Завершён")){
            request();
            try {
                new Thread().sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        service.stopSelf();
    }


    private void request(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app.bb-evacuator.ru/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MyApi api = retrofit.create(MyApi.class);
        Call<Example> result = api.getDriverInfo(key);
        result.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Response<Example> response, Retrofit retrofit) {

                Example result = response.body();
                LatLng driverlatlang = new LatLng(result.getGpsLatitude(),result.getGpsLongitude());
                status = result.getStatusName();

                intent.putExtra("address",customizeText(result.getAddress()));
                intent.putExtra("latlng",driverlatlang);
                intent.putExtra("model",result.getModel().getName());
                intent.putExtra("brand",result.getBrand().getName());
                intent.putExtra("status",status);

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
