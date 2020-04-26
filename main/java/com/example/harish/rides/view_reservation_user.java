package com.example.harish.rides;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class view_reservation_user extends AppCompatActivity {
    DatabaseReservation dbr;
    Button b1;
    //private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation_user);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String sdate_username = extras.getString("EXTRA_username");
        b1 = (Button)findViewById(R.id.vieReserv);
        dbr = new DatabaseReservation(this,null,null,1);




        Cursor cursor = dbr.GetReservations(sdate_username);


        final RadioGroup hourButtonLayout = (RadioGroup) findViewById(R.id.hour_radio_group);

        if(cursor != null && cursor.getCount()>0)
        {
            Integer i =0;
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex("ReservationID"));
                RadioButton rbn = new RadioButton(this);
                rbn.setId(1 + 1000);
                rbn.setText(id);
                //Attach button to RadioGroup.
                hourButtonLayout.addView(rbn);
                i++;
            }
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton TheTextIsHere = (RadioButton) findViewById(hourButtonLayout.getCheckedRadioButtonId());
                Toast.makeText(getApplicationContext(),TheTextIsHere.getText().toString(),Toast.LENGTH_SHORT).show();




                String [] s1 = dbr.GetReservationDetails(sdate_username,TheTextIsHere.getText().toString());
                //Toast.makeText(getApplicationContext(),TheTextIsHere.getText().toString(),Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(view_reservation_user.this, Reservation.class);
                Bundle extras = new Bundle();
                //extras1.putString("EXTRA_sDate",sdate_string);
                extras.putString("EXTRA_sDate",s1[2]);
                extras.putString("EXTRA_eDate",s1[3]);
                extras.putString("EXTRA_AdditionalUtilities",s1[4]);
                extras.putString("EXTRA_amount",s1[6]);
                extras.putString("EXTRA_username",s1[1]);
                extras.putString("EXTRA_carname",s1[5]);
                extras.putString("EXTRA_bool","false");
                //Bundle extras = new Bundle();
                //extras.putString("EXTRA_username",sdate_username);
                //i.putExtras(extras);
                intent1.putExtras(extras);

                //intent1.putExtras(extras1);
                startActivity(intent1);

            }
        });



    }
}
