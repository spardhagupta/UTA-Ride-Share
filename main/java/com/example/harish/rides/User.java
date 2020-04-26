package com.example.harish.rides;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class User extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        b1 = (Button)findViewById(R.id.mreserve);
        b2 = (Button)findViewById(R.id.viewreserva);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String sdate_username = extras.getString("EXTRA_username");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Toast.makeText(getApplicationContext(),"Successfully Login User",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(User.this, MakeReservation.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_username",sdate_username);
                    i.putExtras(extras);
                    startActivity(i);


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(getApplicationContext(),"Successfully Login User",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(User.this, view_reservation_user.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_username",sdate_username);
                //extras.putString("EXTRA_bool","true");
                i.putExtras(extras);
                startActivity(i);


            }
        });
    }
}
