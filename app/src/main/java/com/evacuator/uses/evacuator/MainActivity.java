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
        setContentView(R.layout.activity_main);
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
        Call<Users> usersCall = api.getOrder("1","123","12123");
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Response<Users> response, Retrofit retrofit)
            {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            }

            public void onFailure(Throwable t)
            {
                Toast.makeText(getApplicationContext(), "BAD", Toast.LENGTH_SHORT).show();
            }
        });
    }
}