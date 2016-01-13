package com.evacuator.uses.evacuator.Order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.evacuator.uses.evacuator.Entity.MyApi;
import com.evacuator.uses.evacuator.R;
import com.evacuator.uses.evacuator.Users;
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
public class OrderInfoActivity extends AppCompatActivity implements View.OnClickListener
{
    String myAddres,AddresWhen,idBrand,idModel,car_type,time,sum,gps_longitude,gps_latitude;
    String coment;
    int count_wheels;
    Boolean blocked_wheels,blocked_steering_wheel,low_landing,need_manipul;
    EditText phone,code;
    Button getCode;
    Double weight;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_by_phone);
        phone = (EditText)findViewById(R.id.ET_phone);
        code = (EditText)findViewById(R.id.ET_code);
        getCode = (Button)findViewById(R.id.getCode);

        idBrand = getIntent().getStringExtra("idBrand");
        idModel = getIntent().getStringExtra("idModel");
        time = getIntent().getStringExtra("time");
        weight = getIntent().getDoubleExtra("weight", 0);
        blocked_wheels = getIntent().getBooleanExtra("blocked_wheels", false);
        count_wheels = getIntent().getIntExtra("count_wheels", 0);
        blocked_steering_wheel = getIntent().getBooleanExtra("blocked_steering_wheel", false);
        low_landing = getIntent().getBooleanExtra("low_landing", false);
        car_type = getIntent().getStringExtra("car_type");
        gps_latitude = getIntent().getStringExtra("gps_latitude");
        gps_longitude = getIntent().getStringExtra("gps_longitude");
        myAddres = getIntent().getStringExtra("address");
        need_manipul = getIntent().getBooleanExtra("need_manipul", false);
        AddresWhen = getIntent().getStringExtra("destination_address");
        coment = getIntent().getStringExtra("coment");

        sum = getIntent().getStringExtra("SUM");
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.getCode:
                create_oreder();
                break;
        }
    }

    private void create_oreder()
    {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app.bb-evacuator.ru/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MyApi api = retrofit.create(MyApi.class);
        Users user = new Users();
        user.setBrandId(Integer.valueOf(idBrand));
        user.setModelId(Integer.valueOf(idModel));
        user.setReceiveDate(time);
        user.setWeight(weight);
        user.setBlockedWheels(blocked_wheels);
        user.setBlockedWheelsCnt(count_wheels);
        user.setBlockedSteeringWheel(blocked_steering_wheel);
        user.setLowLanding(low_landing);
        user.setPhone(phone.getText().toString());
        user.setCarType(Integer.valueOf(car_type));
        user.setGpsLongitude(Double.valueOf(gps_longitude));
        user.setGpsLatitude(Double.valueOf(gps_latitude));
        user.setAddress(myAddres);
        user.setManipulatorRequired(need_manipul);
        user.setDestinationAddress(AddresWhen);
        user.setCarType(Integer.valueOf(car_type));
        user.setFromWebsite(true);
        Call<Users> usersCall = api.get(user);
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Response<Users> response, Retrofit retrofit)
            {
                Users s = response.body();
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            }
            public void onFailure(Throwable t)
            {
                Toast.makeText(getApplicationContext(), "BAD", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
