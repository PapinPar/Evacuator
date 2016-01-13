package com.evacuator.uses.evacuator;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.evacuator.uses.evacuator.Entity.MyApi;
import com.evacuator.uses.evacuator.Entity.Verification.entity.Result;
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
 * Created by root on 06.01.16.
 */
public class ValidationActivity extends AppCompatActivity implements View.OnClickListener{

    Button confirm;
    EditText code;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.order_by_phone);
        confirm = (Button)findViewById(R.id.getCode);
    }

    @Override
    public void onClick(View v) {
        request();
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
        String orderId = ""+123;
        String smsCode = code.getText().toString();

        Call<Result> usersCall = api.verifySms(Integer.parseInt(orderId), smsCode);
        usersCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Response<Result> response, Retrofit retrofit)
            {
                Result result = response.body();
                Log.d("result", result.getResult()+" : "+result.getResultMessage());
            }

            public void onFailure(Throwable t) {
                Log.d("SD", "SD2");
            }
        });
    }

}
