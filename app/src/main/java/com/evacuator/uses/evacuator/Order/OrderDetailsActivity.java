package com.evacuator.uses.evacuator.Order;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.evacuator.uses.evacuator.R;


/**
 * Created by root on 06.01.16.
 */
public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener
{
    String myAddres,AddresWhen,idBrand,idModel,car_type,time,tmp,gps_longitude,gps_latitude;
    Double sum,tmpD,weight;
    int count_wheels;
    EditText coment;
    TextView ALL_SUM;
    Boolean blocked_wheels=false,blocked_steering_wheel=false,low_landing=false,need_manipul;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_order);
        count_wheels=0;
        myAddres = getIntent().getStringExtra("myAddres");
        AddresWhen = getIntent().getStringExtra("AddresWhen");
        idBrand = getIntent().getStringExtra("idBrand");
        idModel = getIntent().getStringExtra("idModel");
        car_type = getIntent().getStringExtra("car_type");
        time = getIntent().getStringExtra("time");
        sum = getIntent().getDoubleExtra("SUM", 0);
        weight = getIntent().getDoubleExtra("weight", 0);
        gps_latitude = getIntent().getStringExtra("gps_latitude");
        gps_longitude = getIntent().getStringExtra("gps_longitude");
        need_manipul = getIntent().getBooleanExtra("manipulator_required",false);
        coment = (EditText)findViewById(R.id.coments);
        ALL_SUM = (TextView)findViewById(R.id.all_cost);
        int a = (int) Math.round(sum);
        ALL_SUM.setText(String.valueOf(a));
        Switch block_rule = (Switch) findViewById(R.id.check_3);
        block_rule.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked)
                {
                    blocked_steering_wheel = true;
                    ALL_SUM.setText(String.valueOf(sum + 500));
                } else
                {
                    blocked_steering_wheel = false;
                    tmp = ALL_SUM.getText().toString();
                    tmpD = Double.parseDouble(tmp);
                    ALL_SUM.setText(String.valueOf(tmpD - 500));
                }
            }
        });
        final Switch low_land = (Switch) findViewById(R.id.check_2);
        low_land.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked)
                {
                    low_landing = true;
                } else
                {
                    low_landing = false;
                }
            }
        });
        String[] data = {"0","1","2","3","4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spine;
        spine = (Spinner) findViewById(R.id.spin_wheel);
        spine.setAdapter(adapter);
        spine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position==0)
                    blocked_wheels=false;
                else
                {
                    blocked_wheels=true;
                    count_wheels = position;
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.create_order:
                Intent phone = new Intent(this,OrderInfoActivity.class);
                phone.putExtra("idBrand",String.valueOf(idBrand));
                phone.putExtra("idModel",String.valueOf(idModel));
                phone.putExtra("time",time);
                phone.putExtra("weight",weight);
                phone.putExtra("blocked_wheels",blocked_wheels);
                phone.putExtra("blocked_wheels_cnt",count_wheels);
                phone.putExtra("blocked_steering_wheel",blocked_steering_wheel);
                phone.putExtra("low_landing",low_landing);
                phone.putExtra("car_type",String.valueOf(car_type));
                phone.putExtra("gps_longitude",gps_longitude);
                phone.putExtra("gps_latitude",gps_latitude);
                phone.putExtra("address",myAddres);
                phone.putExtra("manipulator_required",need_manipul);
                phone.putExtra("destination_address",String.valueOf(AddresWhen));
                phone.putExtra("comment",coment.getText().toString());
                //phone.putExtra("SUM",String.valueOf(sum));
                startActivity(phone);
        }
    }
}