package com.evacuator.uses.evacuator.Dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.evacuator.uses.evacuator.R;

import java.util.Calendar;

public class DialogDate extends DialogFragment implements View.OnClickListener{
    final String LOG_TAG = "myLogs";
    final Calendar c = Calendar.getInstance();
    DatePicker pickerDate;
    TextView w,w2;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("calendar");

        View v = inflater.inflate(R.layout.calendar, null);
        v.findViewById(R.id.btnSetData).setOnClickListener(this);
        v.findViewById(R.id.btnBackCal).setOnClickListener(this);
        pickerDate = (DatePicker)v.findViewById(R.id.datePicker);
        pickerDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth)
                    {       /*---*/    }
                });

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSetData:
                int y=pickerDate.getYear();
                int m=pickerDate.getMonth()+1;
                int d=pickerDate.getDayOfMonth();
                w= (TextView) getActivity().findViewById(R.id.date);
                w2= (TextView) getActivity().findViewById(R.id.date_insiv);
                String tmp = w2.getText().toString();
                w.setText(tmp+"  "+Integer.toString(d) + '.' + Integer.toString(m) + '.' + Integer.toString(y));
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