

package com.example.harish.rides;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class Register extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this,null,null,1);
        e1 = (EditText)findViewById(R.id.fname);
        e2 = (EditText)findViewById(R.id.lname);
        e3 = (EditText)findViewById(R.id.gender);
        e4 = (EditText)findViewById(R.id.mobileno);
        e5 = (EditText)findViewById(R.id.email);
        e6 = (EditText)findViewById(R.id.mavid);
        e7 = (EditText)findViewById(R.id.stufac);
        e8 = (EditText)findViewById(R.id.autoclub);
        e9 = (EditText)findViewById(R.id.role);
        e10 = (EditText)findViewById(R.id.uname);
        e11 = (EditText)findViewById(R.id.pass);
        b1 = (Button)findViewById(R.id.register);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                String s6 = e6.getText().toString();
                String s7 = e7.getText().toString();
                String s8 = e8.getText().toString();
                String s9 = e9.getText().toString();
                String s10 = e10.getText().toString();
                String s11 = e11.getText().toString();
                //String s3 = e3.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals("")||s7.equals("")||s8.equals("")||s9.equals("")||s10.equals("")||s11.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_LONG).show();

                }
                else{
                    {
                        Boolean checkEmail = db.checkEmail(s1);
                        if(checkEmail == true){
                            Boolean insert = db.insert(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Email Already Exists",Toast.LENGTH_LONG).show();
                        }
                    }
                    //Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
