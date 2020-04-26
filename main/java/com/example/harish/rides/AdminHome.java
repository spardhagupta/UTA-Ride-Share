package com.example.harish.rides;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        b1 = (Button)findViewById(R.id.viewusers);
        b2 = (Button)findViewById(R.id.viewprofile);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHome.this, Admin_view_user.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getIntent().getStringExtra("EXTRA_username");
                Intent i = new Intent(AdminHome.this, ViewProfile.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_username",username);
                i.putExtras(extras);
                startActivity(i);
            }
        });
    }
}
