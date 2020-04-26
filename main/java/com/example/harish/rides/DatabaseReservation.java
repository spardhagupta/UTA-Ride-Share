package com.example.harish.rides;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseReservation extends SQLiteOpenHelper{

    public DatabaseReservation(Context context,String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name="Reservation.db", factory=null, version=1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table ReservationDetails(ReservationID INTEGER PRIMARY KEY AUTOINCREMENT,UserName text,CarName text,StartDate Datetime,EndDate Datetime,AdditionalUtilities text,Amount Integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
    //inserting in database
    public boolean insert(String UserName,String CarName,String StartDate,String EndDate,String AdditionalUtilities ,String Amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName",UserName);
        contentValues.put("CarName",CarName);
        contentValues.put("StartDate",StartDate);
        contentValues.put("EndDate",EndDate);
        contentValues.put("AdditionalUtilities",AdditionalUtilities);
        contentValues.put("Amount",Amount);
        //contentValues.put("ReservationId",ReservationId);
        //contentValues.put("StudentFaculty",StudentFaculty);
        //contentValues.put("AutoClubMembership",AutoClubMembership);


        long ins = db.insert("ReservationDetails",null,contentValues);
        if(ins==1) return false;
        else return true;


    }

    public Cursor GetReservations(String UserName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from ReservationDetails where UserName=?",new String[]{UserName});
        String[] details = new String[7];

        Log.i("MyApp",UserName);
        return cursor;

        }

    //chekcing the email and password

    public String[] GetReservationDetails(String UserName,String ReservationId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from ReservationDetails where UserName=? and ReservationId=?",new String[]{UserName,ReservationId});
        String[] details = new String[7];
        if(cursor != null && cursor.getCount()>0)

        {   cursor.moveToFirst();
            details[0]  = cursor.getString(cursor.getColumnIndex("ReservationID"));
            details[1]  = cursor.getString(cursor.getColumnIndex("UserName"));
            details[2]  = cursor.getString(cursor.getColumnIndex("StartDate"));
            details[3]  = cursor.getString(cursor.getColumnIndex("EndDate"));
            details[4]  = cursor.getString(cursor.getColumnIndex("AdditionalUtilities"));
            details[5]  = cursor.getString(cursor.getColumnIndex("CarName"));
            details[6]  = cursor.getString(cursor.getColumnIndex("Amount"));
            return details;}
        else {
            details[0] = "None";
            return details;
        }
    }


    public Cursor getRental(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rentalData = db.rawQuery("select ReservationID from ReservationDetails ",new String[]{});
        return rentalData;
    }

    public Integer deleteRental(String Id ){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( "ReservationDetails","ReservationID = ?",new String[] {Id});
    }

    public Cursor getRentalDetails(String RentalId  ) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rentalData = db.rawQuery("select ReservationID, UserName, CarName, StartDate, EndDate, AdditionalUtilities, Amount from ReservationDetails where ReservationID=?",new String[]{RentalId});
        return rentalData;
    }
}
