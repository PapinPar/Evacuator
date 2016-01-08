package com.evacuator.uses.evacuator;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 06.01.16.
 */
public class OrderNewActivity extends AppCompatActivity {

    private LatLng mylatlng;
    private String myAddress;
    private String myId;

    private LatLng destlatlng;
    private String destAddress;
    private String destId;

    private double pathValue;

    TextView date,date_invis, toEdit, fromEdit;
    DialogFragment Calendar,Time;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_new);
        Intent intent = getIntent();
        mylatlng = intent.getParcelableExtra("mylatlng");
        myAddress = (intent.getStringExtra("myAddress"));
        myId = (intent.getStringExtra("myId"));

        destlatlng = intent.getParcelableExtra("destLongitude");
        destAddress = (intent.getStringExtra("destAddress"));
        destId = (intent.getStringExtra("destId"));
        pathValue = intent.getDoubleExtra("path",pathValue);

        toEdit = (TextView)findViewById(R.id.adress_kuda);

        fromEdit = (TextView)findViewById(R.id.adress_otkuda);

        date = (TextView)findViewById(R.id.date);
        date_invis = (TextView)findViewById(R.id.date_insiv);
        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Time = new DialogTime();
                Time.show(getFragmentManager(), "Time");
            }
        });
        date_invis.addTextChangedListener(new TextWatcher()
        {
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }
            @Override
            public void afterTextChanged(Editable s)
            {
                if(!date_invis.getText().toString().equals("Ближайшее"))
                {
                    Calendar = new DialogDate();
                    Calendar.show(getFragmentManager(), "Time");
                }
            }
        });
        request();

    }

    @Override
    protected void onResume() {
        super.onResume();
        toEdit.setText(destAddress);
        fromEdit.setText(myAddress);
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
        //  Call<Users> usersCall = api.getOrder(null,null,null,null,null,null,null,null,"+380638367925","1",null,null,"Украина, Харьков, проспект Людвика Свободы ",null,null,
        //     null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
        Users user = new Users();
        user.setAddress("");
        user.setPhone("");
        user.setCarType(1);
        Call<Users> usersCall = api.get(user);
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Response<Users> response, Retrofit retrofit) {
                Users s = response.body();
                //       s.getAddress();
                //       s.getPhone();
                //      s.getId();
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            }

            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "BAD", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

