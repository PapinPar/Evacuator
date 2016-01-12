package com.evacuator.uses.evacuator;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.evacuator.uses.evacuator.Dialogs.DialogDate;
import com.evacuator.uses.evacuator.Dialogs.DialogTime;
import com.evacuator.uses.evacuator.Entity.Brand.NewBrands;
import com.evacuator.uses.evacuator.Entity.Model.NewModels;
import com.evacuator.uses.evacuator.Entity.MyApi;
import com.evacuator.uses.evacuator.Entity.Tarifs.Tarif;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
public class OrderNewActivity extends AppCompatActivity implements View.OnClickListener {

    private LatLng mylatlng;
    private String myAddress;
    private String myId;
    private String model,brand;
    private LatLng destlatlng;
    private String destAddress;
    private String destId;
    private String wight;

    ArrayList<String> brands = new ArrayList<String>();
    ArrayList<String> modelsId = new ArrayList<String>();
    ArrayList<String> modelsName = new ArrayList<String>();
    ArrayList<String> modelsWight = new ArrayList<String>();
    ArrayList<String> brandsFill = new ArrayList<String>();
    int car_type;
    int idBrand,idModel;
    private double pathValue;
    Spinner spine,spinner;
    TextView date,date_invis, toEdit, fromEdit;
    DialogFragment Calendar,Time;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
        date_invis.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!date_invis.getText().toString().equals("Ближайшее")) {
                    Calendar = new DialogDate();
                    Calendar.show(getFragmentManager(), "Time");
                }
            }
        });
            String[] data = {"Мотоцикл", "Легковая", " Джип \\ Минивэн", " Грузовая",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spine = (Spinner) findViewById(R.id.car_type);
        spinner = (Spinner) findViewById(R.id.spin_brand);
        spine.setAdapter(adapter);
        spine.setPrompt("Тип транспортного средства");
        spine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                car_type= 0;
                if (position == 0)
                    car_type = 6;
                if (position == 1)
                    car_type = 1;
                if (position == 2)
                    car_type = 2;
                if (position == 3)
                    car_type = 4;
                start(car_type);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void start(int position)
    {
        if(position!=6)
        {
            String[] data = {"Мотоцикл", "Легковая", " Джип \\ Минивэн", " Грузовая",};
            LinearLayout line = (LinearLayout)findViewById(R.id.for_auto);
            line.setVisibility(View.VISIBLE);
            request2(position);
        }
        else
        {
            LinearLayout line = (LinearLayout)findViewById(R.id.for_auto);
            line.setVisibility(View.GONE);
        }
    }

    private void request2(final int id)
    {
        brands.clear();
        brandsFill.clear();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app.bb-evacuator.ru/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MyApi api = retrofit.create(MyApi.class);
        Call<List<NewBrands>> usersCall = api.getBrand(String.valueOf(id));
        usersCall.enqueue(new Callback<List<NewBrands>>() {
            @Override
            public void onResponse(Response<List<NewBrands>> response, Retrofit retrofit) {
                List<NewBrands> model = response.body();
                for (int i = 0; i < model.size(); i++) {
                    brandsFill.add(String.valueOf(model.get(i).getName()));
                    brands.add(String.valueOf(model.get(i).getId()));
                }
                spin(id);
            }

            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "BAD", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void spin(final int carType)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, brandsFill);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spin_brand);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                idBrand = position + 1;
                brand = brands.get(position);
                request3(Integer.parseInt(brands.get(position)),carType);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {
                brand = brands.get(0);
            }
        });
        spinner.setPrompt("Марка транспортного средства");
    }

    private void request3(int idBrand,int carType)
    {
        modelsName.clear();
        modelsId.clear();
        modelsWight.clear();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app.bb-evacuator.ru/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MyApi api = retrofit.create(MyApi.class);
        Call<List<NewModels>> usersCall = api.getModel(String.valueOf(idBrand),String.valueOf(carType));
        usersCall.enqueue(new Callback<List<NewModels>>() {
            @Override
            public void onResponse(Response<List<NewModels>> response, Retrofit retrofit) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                List<NewModels> models = response.body();
                for (int i = 0; i < models.size(); i++) {
                    modelsName.add(models.get(i).getName());
                    modelsWight.add(String.valueOf(models.get(i).getWeight()));
                    modelsId.add(String.valueOf(models.get(i).getId()));
                }
                spiner();
            }

            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "BAD", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void spiner()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modelsName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spin_model);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                model = modelsId.get(position);
                wight = modelsWight.get(position);
                request4();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                model = modelsId.get(0);
            }
        });
        spinner.setPrompt("Модель транспортного средства");

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        toEdit.setText(destAddress);
        fromEdit.setText(myAddress);
    }

    private void request4()
    {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app.bb-evacuator.ru/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MyApi api = retrofit.create(MyApi.class);
        Call<List<Tarif>> usersCall = api.getTarif();
        usersCall.enqueue(new Callback<List<Tarif>>() {
            @Override
            public void onResponse(Response<List<Tarif>> response, Retrofit retrofit)
            {
                List<Tarif> tarifs = response.body();
                Log.d("sd","SD");
            }

            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "BAD", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.create_order_1:
                Intent next_order = new Intent(this,OrderDetailsActivity.class);
                if(car_type!=6)
                {
                    if(brand==null)
                        brand = brands.get(0);
                    if(model==null)
                        model = modelsId.get(0);
                }
                next_order.putExtra("myAddres",String.valueOf(myAddress));
                next_order.putExtra("AddresWhen",String.valueOf(destAddress));
                next_order.putExtra("idBrand",String.valueOf(brand));
                next_order.putExtra("idModel",String.valueOf(model));
                next_order.putExtra("car_type",String.valueOf(car_type));
                next_order.putExtra("time",date.getText().toString());
                startActivity(next_order);
                break;
        }
    }
}

