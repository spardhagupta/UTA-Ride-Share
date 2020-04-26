package com.example.harish.rides;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context,String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name="Register.db", factory=null, version=1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table UserDetails(FirstName text,LastName text,Gender text,MobileNo text,Email text,MavId text,StudentFaculty text,AutoClub text,Role text,UserName text primary key,Password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
//inserting in database
    public boolean insert(String FirstName,String LastName, String Gender, String MobileNo,String Email,String MavId,String StudentFaculty,String AutoClub,String Role, String UserName,String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName",FirstName);
        contentValues.put("LastName",LastName);
        contentValues.put("Gender",Gender);
        contentValues.put("MobileNo",MobileNo);
        contentValues.put("Email",Email);
        contentValues.put("MavId",MavId);
        contentValues.put("StudentFaculty",StudentFaculty);
        contentValues.put("AutoClub",AutoClub);
        contentValues.put("Role",Role);
        contentValues.put("UserName",UserName);
        contentValues.put("Password",Password);

        long ins = db.insert("UserDetails",null,contentValues);
        if(ins==1) return false;
        else return true;


    }
//checking if email exists
    public Boolean checkEmail(String UserName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from UserDetails where UserName=?",new String[]{UserName});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    //chekcing the email and password

    public String emailpassword(String UserName,String Password){
        SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery("select * from UserDetails where UserName=? and Password=?",new String[]{UserName,Password});

         if(cursor != null && cursor.getCount()>0)

         {   cursor.moveToFirst();
             String id = cursor.getString(cursor.getColumnIndex("Role"));
             return id;}
         else return "None";
    }

    public Cursor getprofiledata(String UserName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor profiledata = db.rawQuery("select FirstName,LastName, MobileNo, Email, MavId, AutoClub, Gender, UserName, Password from UserDetails where UserName=?",new String[]{UserName});
        return profiledata;
    }

    public Cursor getallusers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor allusers = db.rawQuery("select FirstName,LastName, MobileNo, Email, MavId, AutoClub, Gender, UserName, Password from UserDetails",null);
        return allusers;
    }

    public boolean updateprofile(String FirstName,String LastName,String  MobileNo,String  Email,String  MavId,String  AutoClub,String  Gender,String  UserName,String  Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName",FirstName);
        contentValues.put("LastName",LastName);
        contentValues.put("MobileNo",MobileNo);
        contentValues.put("Email",Email);
        contentValues.put("MavId",MavId);
        contentValues.put("AutoClub",AutoClub);
        contentValues.put("Gender",Gender);
        contentValues.put("Password",Password);
        db.update("UserDetails",contentValues,"UserName=?",new String[]{UserName});
        return true;
    }



}
