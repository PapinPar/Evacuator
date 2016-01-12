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
import android.widget.RelativeLayout;
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

import java.math.BigDecimal;
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
    private Boolean one=false,two=false,three=false;

    private ArrayList<String> brands = new ArrayList<String>();
    private ArrayList<String> TarifsInfo = new ArrayList<String>();
    private  ArrayList<String> modelsId = new ArrayList<String>();
    private ArrayList<String> modelsName = new ArrayList<String>();
    private ArrayList<String> modelsWight = new ArrayList<String>();
    private ArrayList<String> brandsFill = new ArrayList<String>();
    private  int car_type;
    private int idBrand,idModel;
    private double pathValue;
    Spinner spine,spinner;
    RelativeLayout ModLay,BrandLay;
    TextView date,date_invis, toEdit, fromEdit;
    DialogFragment Calendar,Time;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        ModLay = (RelativeLayout)findViewById(R.id.modelLay);
        ModLay.setVisibility(View.INVISIBLE);
        BrandLay = (RelativeLayout)findViewById(R.id.brandLay);
        BrandLay.setVisibility(View.INVISIBLE);
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
        String[] data = {"Выберите тип ТС","Мотоцикл", "Легковая", " Джип \\ Минивэн", " Грузовая",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spine = (Spinner) findViewById(R.id.car_type);
        spinner = (Spinner) findViewById(R.id.spin_brand);
        spine.setAdapter(adapter);
        spine.setPrompt("Тип транспортного средства");
        spine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(one==true)
                {
                    if(position!=0)
                    {
                        car_type= 0;
                        if (position == 1)
                            car_type = 6;
                        if (position == 2)
                            car_type = 1;
                        if (position == 3)
                            car_type = 2;
                        if (position == 4)
                            car_type = 4;
                        start(car_type);
                    }
                }
                else
                    one=true;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void start(int position)
    {
        if(position!=6&&position!=0)
        {
            LinearLayout line = (LinearLayout)findViewById(R.id.for_auto);
            line.setVisibility(View.VISIBLE);
            BrandLay.setVisibility(View.VISIBLE);
            getBrand(position);
        }
        else
        {
            LinearLayout line = (LinearLayout)findViewById(R.id.for_auto);
            line.setVisibility(View.INVISIBLE);
        }
    }

    private void getBrand(final int id)
    {
        brands.clear();
        brandsFill.clear();
        brandsFill.add("Выберите марку");
        brands.add("");
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
                fillBrand(id);
            }

            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "BAD connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillBrand(final int carType)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, brandsFill);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spin_brand);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position==0)
                    ModLay.setVisibility(View.INVISIBLE);
                if(two==true&&position!=0)
                {
                    idBrand = position + 1;
                    brand = brands.get(position);
                    ModLay.setVisibility(View.VISIBLE);
                    getModel(Integer.parseInt(brands.get(position)), carType);
                }
                else
                    two=true;
            }
            public void onNothingSelected(AdapterView<?> parent)
            {
                brand = brands.get(0);
            }
        });
        spinner.setPrompt("Марка транспортного средства");
    }

    private void getModel(int idBrand,int carType)
    {
        modelsName.clear();
        modelsId.clear();
        modelsWight.clear();

        modelsId.add("");
        modelsName.add("Выберите Модель");
        modelsWight.add("");
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
                fillModel();
            }

            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "BAD Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillModel()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modelsName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spin_model);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (three == true && position != 0) {
                    model = modelsId.get(position);
                    wight = modelsWight.get(position);
                    getWight();
                } else
                    three = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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

    private void getWight()
    {
        TarifsInfo.clear();
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
            public void onResponse(Response<List<Tarif>> response, Retrofit retrofit) {
                List<Tarif> tarifs = response.body();
                Double Wight = Double.parseDouble(wight);
                for (int i = 0; i < tarifs.size(); i++) {
                    if (tarifs.get(i).getId() != 11) {
                        if (tarifs.get(i).getWeightFrom() <= Wight && tarifs.get(i).getWeightTo() >= Wight && tarifs.get(i).getCarTypes().get(0) == car_type) {
                            TarifsInfo.add(tarifs.get(i).getName());
                            TarifsInfo.add(String.valueOf(tarifs.get(i).getPriceLoading()));
                            TarifsInfo.add(String.valueOf(tarifs.get(i).getPriceKm()));
                            TarifsInfo.add(String.valueOf(tarifs.get(i).getPriceMinute()));
                            TarifsInfo.add(String.valueOf(Wight));
                        }
                    }
                }
                setWight();
            }

            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "BAD connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setWight()
    {
        TextView priceLoad = (TextView)findViewById(R.id.price_load);
        priceLoad.setText(TarifsInfo.get(1).toString());
        TextView priceKm = (TextView)findViewById(R.id.price_kmAndMin);
        priceKm.setText("далее 1км.("+TarifsInfo.get(2).toString()+" руб.) + 1 мин.("+TarifsInfo.get(3).toString()+" руб.)");
    }


    @Override
    public void onClick(View v)
    {
        int pathTime = getIntent().getIntExtra("pathTime", 0);
        Double pathValue = getIntent().getDoubleExtra("path", 0);
        Double sum = Double.parseDouble(TarifsInfo.get(1));
        sum = sum +pathValue*Double.parseDouble(TarifsInfo.get(2));
        BigDecimal s2;
        s2 = new BigDecimal(String.valueOf(sum));
        s2.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        sum = Double.parseDouble(String.valueOf(s2));
        //sum = sum +(Integer.parseInt(pathTime)*Integer.parseInt(TarifsInfo.get(3)));
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
                next_order.putExtra("SUM",sum);
                next_order.putExtra("tarif_name",TarifsInfo.get(0).toString());

                    startActivity(next_order);
                break;
        }
    }

}

