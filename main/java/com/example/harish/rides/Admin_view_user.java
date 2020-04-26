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

public class Admin_view_user extends AppCompatActivity {
    DatabaseHelper db;
    Button detailbutton;
    RadioGroup rgroup;
    RadioButton rbutton;
    int rg_id = 33;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.userlist);
        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.setId(rg_id);
        RadioGroup.LayoutParams rgl;
        db = new DatabaseHelper(this,null,null,1);
        Cursor allusers = db.getallusers();

        while(allusers.moveToNext()){
            RadioButton r1 = new RadioButton(this);
            r1.setText(allusers.getString(7));
            rgl = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,RadioGroup.LayoutParams.MATCH_PARENT);
            rg.addView(r1, rgl);
        }
        rl.addView(rg);
        rgroup = (RadioGroup) findViewById(rg_id);
        detailbutton = (Button)findViewById(R.id.detailbutton);
        detailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedid = rgroup.getCheckedRadioButtonId();
                rbutton = (RadioButton) findViewById(checkedid);
                String username = rbutton.getText().toString();
                Intent i = new Intent(Admin_view_user.this, ViewProfile.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_username",username);
                i.putExtras(extras);
                startActivity(i);

            }
        });

    }
}
