package com.evacuator.uses.evacuator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by root on 06.01.16.
 */
public class OrderDetailsActivity extends AppCompatActivity
{
    String myAddres,AddresWhen,idBrand,idModel,car_type,time;
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
    }
}