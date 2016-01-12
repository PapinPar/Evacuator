package com.evacuator.uses.evacuator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.evacuator.uses.evacuator.Entity.MyApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 06.01.16.
 */
public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener
{
    String myAddres,AddresWhen,idBrand,idModel,car_type,time,tmp,tarif_name;
    Double sum,tmpD;
    EditText coment;
    TextView ALL_SUM;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_order);
        myAddres = getIntent().getStringExtra("myAddres");
        AddresWhen = getIntent().getStringExtra("AddresWhen");
        idBrand = getIntent().getStringExtra("idBrand");
        idModel = getIntent().getStringExtra("idModel");
        car_type = getIntent().getStringExtra("car_type");
        time = getIntent().getStringExtra("time");
        sum = getIntent().getDoubleExtra("SUM", 0);
        tarif_name = getIntent().getStringExtra("tarif_name");
        coment = (EditText)findViewById(R.id.coments);
        ALL_SUM = (TextView)findViewById(R.id.all_cost);
        Switch mySwitch = (Switch) findViewById(R.id.check_3);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    ALL_SUM.setText(String.valueOf(sum + 500));
                } else {
                    tmp = ALL_SUM.getText().toString();
                    tmpD = Double.parseDouble(tmp);
                    ALL_SUM.setText(String.valueOf(tmpD - 500));
                }
            }
        });
        String[] data = {"0","1","2","3","4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spine;
        spine = (Spinner) findViewById(R.id.spin_wheel);
        spine.setAdapter(adapter);
        spine.setPrompt("Тип транспортного средства");
        spine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
            }
            public void onNothingSelected(AdapterView<?> parent)
            {}
        });
        ALL_SUM.setText(String.valueOf(sum));
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
        user.setAddress(myAddres);
        user.setPhone("+380638367925");
        user.setCarType(Integer.valueOf(car_type));
        user.setDestinationAddress(AddresWhen);
        user.setModelId(Integer.valueOf(idModel));
        user.setBrandId(Integer.valueOf(idBrand));
        user.setComment(coment.toString());
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

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.create_order:
                create_oreder();
        }
    }
}