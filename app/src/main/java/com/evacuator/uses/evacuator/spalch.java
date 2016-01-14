package com.evacuator.uses.evacuator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.evacuator.uses.evacuator.Order.OrderNewActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 13.01.16.
 */
public class spalch extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slapsh);
        ImageView splach = (ImageView)findViewById(R.id.splach_iamge);
        splach.setImageResource(R.drawable.spash);
        if(splach.getDrawable()!=null)
            spalch1();
    }

    private void spalch1()
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                go();
            }
        }, 2000);

    }

    private void go()
    {
        Intent inttent = new Intent(this,OrderNewActivity.class);
        startActivity(inttent);
    }
}

