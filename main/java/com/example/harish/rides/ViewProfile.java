package com.example.harish.rides;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewProfile extends AppCompatActivity {
    DatabaseHelper db;
    Button updatebutton,revokebutton;
    public EditText textView,textdy0,textdy1,textdy2,textdy3,textdy4,textdy5,textdy6,textdy7,textdy8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        db = new DatabaseHelper(this,null,null,1);
        String username = getIntent().getStringExtra("EXTRA_username");
        Cursor profiledata = db.getprofiledata(username);
        updatebutton = (Button)findViewById(R.id.UpdateProfile);
//        revokebutton = (Button)findViewById(R.id.RevokeRenter);

        if(profiledata.getCount() == 0){
            showmsg("Error","Something Wrong");
            return;
        }
        if(profiledata.moveToNext()){
            textView = (EditText)findViewById(R.id.dy0);
            textView.setText(profiledata.getString(0));

            textView = (EditText)findViewById(R.id.dy1);
            textView.setText(profiledata.getString(1));

            textView = (EditText)findViewById(R.id.dy2);
            textView.setText(profiledata.getString(2));

            textView = (EditText)findViewById(R.id.dy3);
            textView.setText(profiledata.getString(3));

            textView = (EditText)findViewById(R.id.dy4);
            textView.setText(profiledata.getString(4));

            textView = (EditText)findViewById(R.id.dy5);
            textView.setText(profiledata.getString(5));

            textView = (EditText)findViewById(R.id.dy6);
            textView.setText(profiledata.getString(6));

            textView = (EditText)findViewById(R.id.dy7);
            textView.setText(profiledata.getString(7));

            textView = (EditText)findViewById(R.id.dy8);
            textView.setText(profiledata.getString(8));
        }

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textdy0 = (EditText)findViewById(R.id.dy0);
                textdy1 = (EditText)findViewById(R.id.dy1);
                textdy2 = (EditText)findViewById(R.id.dy2);
                textdy3 = (EditText)findViewById(R.id.dy3);
                textdy4 = (EditText)findViewById(R.id.dy4);
                textdy5 = (EditText)findViewById(R.id.dy5);
                textdy6 = (EditText)findViewById(R.id.dy6);
                textdy7 = (EditText)findViewById(R.id.dy7);
                textdy8 = (EditText)findViewById(R.id.dy8);

                boolean isProfileUpdated = db.updateprofile(textdy0.getText().toString(),textdy1.getText().toString(),textdy2.getText().toString(),textdy3.getText().toString(),textdy4.getText().toString(),textdy5.getText().toString(),textdy6.getText().toString(),textdy7.getText().toString(),textdy8.getText().toString());
                if(isProfileUpdated == true){
                    Toast.makeText(getApplicationContext(),"Profile Updated Successfully",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ViewProfile.this,ViewProfile.class);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Profile Not Updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void showmsg(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
    }

}
