package com.example.harish.rides;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AvailableCars extends AppCompatActivity {

    DatabaseCar dbcar;
    private SQLiteDatabase mDb;
    Button cAmount;
    //public RelativeLayout layoutmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_cars);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String sdate_string = extras.getString("EXTRA_sDate");
        final String edate_string = extras.getString("EXTRA_eDate");
        final String gps_string = extras.getString("EXTRA_GPS");
        final String onstar_string = extras.getString("EXTRA_OnStar");
        final String sirius_string = extras.getString("EXTRA_Sirius");
        final String capacity_string = extras.getString("EXTRA_Capacity");
        final String sdate_username = extras.getString("EXTRA_username");
        cAmount = (Button)findViewById(R.id.cAmount);
        TextView v;

        //v = (TextView)findViewById(R.id.textView);


        dbcar = new DatabaseCar(this);

        try {
            dbcar.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        mDb = dbcar.getWritableDatabase();
        Date sdate;
        Date edate;

        //String yes = "2015-01-12";
        //String query = "select * from CarDetails where DateStart<=" + "DATETIME(" + sdate_string + ")" + " AND DateEnd>=" + "DATETIME(" + edate_string + ")" + " AND Capacity=" + capacity_string;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            sdate = dateFormat.parse(sdate_string);
            edate = dateFormat.parse(edate_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String query = "select * from CarDetails where DateStart<=DATETIME('"+ sdate_string + "')" + " AND DateEnd>=DATETIME('" + edate_string + "')" + " AND Capacity = " + capacity_string + ";";
        String query = "select * from CarDetails where Capacity=" + capacity_string + " AND " + "DateStart<=DATETIME('"+ sdate_string + "')" ;
        Log.d("MyApp",query);
        //Cursor cursor =  mDb.rawQuery("select * from CarDetails where DateStart<=? AND DateEnd>=? AND Capacity = ?",new String[]{sdate,edate_string,capacity_string});
        Cursor cursor = mDb.rawQuery(query,null);
        //Log.d("cursor",);


        final RadioGroup hourButtonLayout = (RadioGroup) findViewById(R.id.hour_radio_group);

        if(cursor != null && cursor.getCount()>0)

        { Integer i =0;
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex("CarName"));
                RadioButton rbn = new RadioButton(this);
                rbn.setId(1 + 1000);
                rbn.setText(id);
                //Attach button to RadioGroup.
                hourButtonLayout.addView(rbn);
                i++;


            }


        }
        cAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton TheTextIsHere = (RadioButton) findViewById(hourButtonLayout.getCheckedRadioButtonId());


                Toast.makeText(getApplicationContext(),TheTextIsHere.getText().toString(),Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(AvailableCars.this, CalculateAmount.class);
                Bundle extras1 = new Bundle();
                extras1.putString("EXTRA_sDate",sdate_string);
                extras1.putString("EXTRA_eDate",edate_string);
                extras1.putString("EXTRA_GPS",gps_string);
                extras1.putString("EXTRA_OnStar",onstar_string);
                extras1.putString("EXTRA_Sirius",sirius_string);
                extras1.putString("EXTRA_CarName",TheTextIsHere.getText().toString());
                extras1.putString("EXTRA_Capacity",capacity_string);
                extras1.putString("EXTRA_username",sdate_username);
                intent1.putExtras(extras1);
                startActivity(intent1);

            }
        });








    }
}
