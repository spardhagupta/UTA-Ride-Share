package com.example.harish.rides;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reservation extends AppCompatActivity {
    TextView sDate,endDate,carname,amount,au;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        sDate = (TextView)findViewById(R.id.sdate);
        endDate = (TextView)findViewById(R.id.editText8);
        carname = (TextView)findViewById(R.id.cname);
        amount = (TextView)findViewById(R.id.amt);
        au = (TextView) findViewById(R.id.au);
        b1 = (Button) findViewById(R.id.homescreen);

        Intent intent1 = getIntent();
        Bundle extras1 = intent1.getExtras();

        final String username_string = extras1.getString("EXTRA_username");
        final String bool_string = extras1.getString("EXTRA_bool");


        //Toast.makeText(getApplicationContext(),carname_string,Toast.LENGTH_SHORT).show();
        if(bool_string.equals("false")){

            final String carname_string = extras1.getString("EXTRA_carname");
            final String sdate_string = extras1.getString("EXTRA_sDate");
            final String edate_string = extras1.getString("EXTRA_eDate");
            final String amount_string = extras1.getString("EXTRA_amount");
            final String additionalUtilities_string = extras1.getString("EXTRA_AdditionalUtilities");
        sDate.setText(sdate_string);
        endDate.setText(edate_string);
        carname.setText(carname_string);
        amount.setText(amount_string);
        au.setText(additionalUtilities_string);}

        else
        {
            Toast.makeText(getApplicationContext(),"works",Toast.LENGTH_SHORT).show();
        }



        //final String sdate_string = extras1.getString("EXTRA_sDate");


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    //Toast.makeText(getApplicationContext(), "Reservation Confirmed", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Reservation.this, User.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_username",username_string);
                    i.putExtras(extras);
                    startActivity(i);






            }
        });



    }
}
