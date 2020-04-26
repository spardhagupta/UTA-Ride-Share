package com.example.harish.rides;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class  MainActivity extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1,b2;
    DatabaseHelper db;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this,null,null,1);

        e1 = (EditText)findViewById(R.id.userlogin);
        e2 = (EditText)findViewById(R.id.passlogin);
        //e3 = (EditText)findViewById(R.id.editText2);
        b1 = (Button)findViewById(R.id.login);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = e1.getText().toString();
                String password = e2.getText().toString();
                String Chkemailpass = db.emailpassword(username, password);
                if (Chkemailpass.equals("User")) {
                    Toast.makeText(getApplicationContext(), "Successfully Login User", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, User.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_username", username);
                    i.putExtras(extras);
                    startActivity(i);

                } else if (Chkemailpass.equals("Admin")) {
                    Toast.makeText(getApplicationContext(), "Successfully Login Admin", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, AdminHome.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_username",username);
                    i.putExtras(extras);
                    startActivity(i);

                } else if (Chkemailpass.equals("Manager")){
                    Toast.makeText(getApplicationContext(), "Successfully Login Manager", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, Manager_Home.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),Chkemailpass,Toast.LENGTH_SHORT).show();
            }
        });

        b2 = (Button)findViewById(R.id.Register);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });


    }

}
