package com.evacuator.uses.evacuator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by root on 06.01.16.
 */
public class OrderDetailsActivity extends AppCompatActivity
{
    String myAddres,AddresWhen,idBrand,idModel,car_type,time;
    int sum;
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
        sum = getIntent().getIntExtra("SUM",0);
    }
    public void onCheckboxClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.check_1:
                if (checked)
                {
                    Toast.makeText(getApplicationContext(),"asd",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.check_2:
                if (checked)
                {

                }
                else
                {

                }
                break;
            case R.id.check_3:
                if (checked)
                {

                }
                else
                {

                }
                break;
        }
    }
}