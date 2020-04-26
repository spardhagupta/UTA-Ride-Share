package com.example.harish.rides;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class CalculateAmount extends AppCompatActivity {
    Date sdate;
    Date edate;
    String s_date_form;
    Date e_date_form;
    String WeekDayRate;
    String WeekEndRate;
    EditText calcamount;
    EditText tax;
    EditText finalamount;
    Button confirmReservation;

    DatabaseCar dbcar;
    DatabaseReservation dbr;
    private SQLiteDatabase mDb;
    String AdditionalUtilities;


    public static double[] getWorkingDaysBetweenTwoDates(Date startDate, Date endDate,SQLiteDatabase mDb,String carName_string,String WeekDayRate,String WeekEndRate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int workDays = 0;
        int weekEnd = 0;



        //Return 0 if start and end are the same
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            double ar[] = new double[1];
            ar[0]= 0;
            return ar;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }

        do {
            //excluding start date
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
            else{
                ++weekEnd;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
        String query = "select * from CarDetails where CarName='" + carName_string + "'" ;
        Log.d("MyApp",query);
        //Cursor cursor =  mDb.rawQuery("select * from CarDetails where DateStart<=? AND DateEnd>=? AND Capacity = ?",new String[]{sdate,edate_string,capacity_string});
        Cursor cursor = mDb.rawQuery("select * from CarDetails where CarName=?",new String[]{carName_string});

        if(cursor != null && cursor.getCount()>0)

        {   cursor.moveToFirst();
            WeekDayRate = cursor.getString(cursor.getColumnIndex("WeekDayRate"));
            WeekEndRate = cursor.getString(cursor.getColumnIndex("WeekEndRate"));
            }

        double IntWeekDayRate = Double.parseDouble(WeekDayRate);
        double IntWeekEndRate = Double.parseDouble(WeekEndRate);
        //Integer IntWeekDayRate = Integer.parseInt(WeekDayRate);
        //Integer IntWeekEndRate = Integer.parseInt(WeekEndRate);

        double Amount = IntWeekDayRate*workDays + IntWeekEndRate*weekEnd;
        double TaxAmount = (0.05)*Amount;
        double FinalAmount = Amount+ TaxAmount;
        double ar[] = new double[3];
        ar[0]= Amount;
        ar[1] =  TaxAmount;
        ar[2] =  FinalAmount;
        return ar;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_amount);
        calcamount = (EditText) findViewById(R.id.amount);
        tax = (EditText) findViewById(R.id.tax);
        finalamount = (EditText) findViewById(R.id.finamount);
        confirmReservation = (Button)findViewById(R.id.button);
        Intent intent1 = getIntent();
        Bundle extras1 = intent1.getExtras();
        final String sdate_string = extras1.getString("EXTRA_sDate");
        final String edate_string = extras1.getString("EXTRA_eDate");
        final String gps_string = extras1.getString("EXTRA_GPS");
        final String onstar_string = extras1.getString("EXTRA_OnStar");
        final String sirius_string = extras1.getString("EXTRA_Sirius");
        final String capacity_string = extras1.getString("EXTRA_Capacity");
        final String carName_string = extras1.getString("EXTRA_CarName");
        final String sdate_username = extras1.getString("EXTRA_username");


        if(gps_string.equals("true") && onstar_string.equals("true") && sirius_string.equals("true")){

            AdditionalUtilities = "GPS" + "On Star" + "SiriusXM";

        }
        else if(gps_string.equals("true") && onstar_string.equals("false") && sirius_string.equals("true")){

            AdditionalUtilities = "GPS" + "SiriusXM";

        }

        else if(gps_string.equals("true") && onstar_string.equals("true") && sirius_string.equals("false")){

            AdditionalUtilities = "GPS" + "On Star";

        }
        else if(gps_string.equals("false") && onstar_string.equals("true") && sirius_string.equals("false")){

            AdditionalUtilities = "On Star" + "SiriusXM";

        }
        else if(gps_string.equals("false") && onstar_string.equals("false") && sirius_string.equals("true")){

            AdditionalUtilities =  "SiriusXM";

        }

        else if(gps_string.equals("true") && onstar_string.equals("false") && sirius_string.equals("false")){

            AdditionalUtilities =   "GPS";

        }

        else if(gps_string.equals("false") && onstar_string.equals("true") && sirius_string.equals("false")){

            AdditionalUtilities =  "On Star";

        }
        else{
            AdditionalUtilities =  "None";

        }

        //String dtStart = "2010-10-15T09:27:37Z";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdate = format.parse(sdate_string);
            //s_date_form = outputFormat.format(sdate);
            edate = format.parse(edate_string);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        dbcar = new DatabaseCar(this);
        mDb = dbcar.getWritableDatabase();
        double[] Amount = getWorkingDaysBetweenTwoDates(sdate,edate,mDb,carName_string,WeekDayRate,WeekEndRate);
        calcamount.setText(String.valueOf(Amount[0]));
        tax.setText(String.valueOf(Amount[1]));
        finalamount.setText(String.valueOf(Amount[2]));
        Log.d("MyApp",sdate.toString());
        Log.d("MyApp",edate.toString());

        dbr = new DatabaseReservation(this,null,null,1);

        confirmReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Boolean ins = dbr.insert(sdate_username,carName_string,sdate_string,edate_string,AdditionalUtilities,finalamount.getText().toString());

                 if(ins == true){

                     Toast.makeText(getApplicationContext(), "Reservation Confirmed", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(CalculateAmount.this, Reservation.class);
                     Bundle extras = new Bundle();
                     extras.putString("EXTRA_sDate",sdate_string.toString());
                     extras.putString("EXTRA_eDate",edate_string.toString());
                     extras.putString("EXTRA_AdditionalUtilities",AdditionalUtilities);
                     extras.putString("EXTRA_amount",finalamount.getText().toString());
                     extras.putString("EXTRA_username",sdate_username);
                     extras.putString("EXTRA_carname",carName_string);
                     extras.putString("EXTRA_bool","false");
                     //Bundle extras = new Bundle();
                     extras.putString("EXTRA_username",sdate_username);
                     //i.putExtras(extras);
                     intent.putExtras(extras);
                     startActivity(intent);

                }


            }
        });
        //
    }

}
