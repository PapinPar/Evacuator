package com.evacuator.uses.evacuator;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class DialogTime extends DialogFragment implements View.OnClickListener{
    final String LOG_TAG = "myLogs";
    int ho,mi;
    final Calendar c = Calendar.getInstance();
    TimePicker timepicker;
    TextView w;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("calendar");
        final Calendar c = Calendar.getInstance();
        View v = inflater.inflate(R.layout.time, null);
        v.findViewById(R.id.btnSetData).setOnClickListener(this);
        v.findViewById(R.id.btnBackCal).setOnClickListener(this);
        timepicker = (TimePicker)v.findViewById(R.id.timePicker);
        timepicker.setIs24HourView(true);
        timepicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));ho=c.get(Calendar.HOUR_OF_DAY);
        timepicker.setCurrentMinute(c.get(Calendar.MINUTE));mi = c.get(Calendar.MINUTE);
        timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                ho=hourOfDay;mi=minute;
            }
        });

        return v;
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSetData:
                int y=ho;
                int m=mi;
                w= (TextView) getActivity().findViewById(R.id.date_insiv);
                w.setText(Integer.toString(y)+":"+Integer.toString(m));
                dismiss();
                break;
            case R.id.btnBackCal:
                w= (TextView) getActivity().findViewById(R.id.date);
                w.setText("Ближайшее");
                dismiss();
                break;
        }
    }

}