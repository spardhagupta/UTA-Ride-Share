package com.example.harish.rides;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class ManagerViewRental extends AppCompatActivity {
    DatabaseReservation dbr;
    RadioGroup rgroup;
    RadioButton rbutton;
    Button rentaldetail;
    int rg_id = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_rental);
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.userlist);
        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.setId(rg_id);
        RadioGroup.LayoutParams rgl;
        dbr = new DatabaseReservation(this,null,null,1);
        Cursor allRentals = dbr.getRental();

        while(allRentals.moveToNext()){
            RadioButton r1 = new RadioButton(this);
            r1.setText(allRentals.getString(0));
            rgl = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,RadioGroup.LayoutParams.MATCH_PARENT);
            rg.addView(r1, rgl);
        }
        rl.addView(rg);
        rgroup = (RadioGroup) findViewById(rg_id);
        rentaldetail = (Button)findViewById(R.id.rentaldetail);
        rentaldetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedid = rgroup.getCheckedRadioButtonId();
                rbutton = (RadioButton) findViewById(checkedid);
                String RentalId = rbutton.getText().toString();
                Intent i = new Intent(ManagerViewRental.this, ViewRentalDetails.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_username",RentalId);
                i.putExtras(extras);
                startActivity(i);

            }
        });

    }
}