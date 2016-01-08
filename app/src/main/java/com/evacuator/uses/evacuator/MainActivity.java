package com.evacuator.uses.evacuator;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
{
    TextView date,date_invis;
    DialogFragment Calendar,Time;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_new);
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
    }

}
