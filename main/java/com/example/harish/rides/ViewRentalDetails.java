package com.example.harish.rides;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewRentalDetails extends AppCompatActivity {
    DatabaseReservation dbr;
    Button deleteButton;
    public EditText textView,textdy0,textdy1,textdy2,textdy3,textdy4,textdy5,textdy6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rental_details);
        dbr = new DatabaseReservation(this,null,null,1);
        final String RentalId = getIntent().getStringExtra("EXTRA_username");
        final Cursor profiledata = dbr.getRentalDetails(RentalId);
        deleteButton = (Button)findViewById(R.id.deleteRental);



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
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRow = dbr. deleteRental(RentalId);
                if (deletedRow > 0){
                    Toast.makeText(getApplicationContext(),"Rental Deleted Successfully",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Rental Not Found",Toast.LENGTH_LONG).show();
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