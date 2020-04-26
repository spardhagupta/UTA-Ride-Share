package com.example.harish.rides;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

import java.util.Calendar;

public class MakeReservation extends AppCompatActivity {
    Calendar c;
    TextView myDate,endDate,sTime,eTime,capacity;
    Button SearchCar;
    CheckBox g,s,o;
    TimePicker t;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    DatePickerDialog.OnDateSetListener mDateSetListener2;

    TimePickerDialog.OnTimeSetListener onTimeSetListener;
    TimePickerDialog.OnTimeSetListener onTimeSetListener2;
    String dateStart;
    String dateEnd;
    String TimeStart;
    String TimeEnd;
    String Capacity;
    String GPS_n = "false";
    String Sirius_n = "false";
    String OnStar_n = "false";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String sdate_username = extras.getString("EXTRA_username");
        //calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView)findViewById(R.id.sdate);
        endDate = (TextView)findViewById(R.id.eDate);
        sTime = (TextView)findViewById(R.id.stime);
        eTime = (TextView)findViewById(R.id.etime);
        SearchCar = (Button)findViewById(R.id.SearchCar);
        g = (CheckBox) findViewById(R.id.checkBox);
        o = (CheckBox) findViewById(R.id.checkBox2);
        s = (CheckBox) findViewById(R.id.checkBox3);
        capacity = (TextView) findViewById(R.id.capacity);

        sTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                System.out.print("Hello");

                TimePickerDialog timePickerDialog = new TimePickerDialog(MakeReservation.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                TimeStart = hourOfDay + ":" + minute + ":00";
                                sTime.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        eTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                System.out.print("Hello");

                TimePickerDialog timePickerDialog = new TimePickerDialog(MakeReservation.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                TimeEnd = hourOfDay + ":" + minute +":"+ "00";
                                eTime.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        myDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                System.out.print("Hello");

                DatePickerDialog dialog = new DatePickerDialog(
                        MakeReservation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        MakeReservation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                dateStart= year + "-" + month + "-" + day;
                myDate.setText(month + "/" + day + "/" + year);
            }
        };

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                //tring date = month + "/" + day + "/" + year;
                dateEnd= year + "-" + month + "-" + day;
                endDate.setText(month + "/" + day + "/" + year);
            }
        };

        SearchCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Start = dateStart + " " + TimeStart;
                String End = dateEnd + " " + TimeEnd;
                String Capacity = capacity.getText().toString();

                if(g.isChecked())
                    GPS_n = "true"; // you can save this as checked somewhere
                if(o.isChecked())
                    OnStar_n = "true"; // you can save this as checked somewhere
                if(s.isChecked())
                    Sirius_n = "true"; // you can save this as checked somewhere

                Intent intent = new Intent(MakeReservation.this, AvailableCars.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_sDate",Start);
                extras.putString("EXTRA_eDate",End);
                extras.putString("EXTRA_GPS",GPS_n);
                extras.putString("EXTRA_OnStar",OnStar_n);
                extras.putString("EXTRA_Sirius",Sirius_n);
                extras.putString("EXTRA_Capacity",Capacity);
                //Bundle extras = new Bundle();
                extras.putString("EXTRA_username",sdate_username);
                //i.putExtras(extras);
                intent.putExtras(extras);
                startActivity(intent);

            }
        });




    }

    }

