package com.esprit.lunar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DBNAME = "MyDB.db";
    private static final String TABLE_NAME = "partsList";
    private static final String COLUMN_BRAND = "brand";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SERIALNUMBER = "_serialNumber";
    private static final String COLUMN_QUANTITY = "quantity";

    DBHelper(Context context ) {
        super(context, "MyDB.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (emaill TEXT primary key, password TEXT)");

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_BRAND + " TEXT, " +
                COLUMN_YEAR + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SERIALNUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT);" +
                COLUMN_QUANTITY + " TEXT);";
        MyDB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists partsList");
    }

    public Boolean insertData(String emaill, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("emaill", emaill);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkuseremail(String emaill) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where emaill = ?", new String[]{emaill});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String emaill, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where emaill = ? and password = ?", new String[] {emaill,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getData(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from partsList",null);
        return cursor;
    }

    void updateData(String brand, String year, String name, String row_serialNumber, String quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BRAND,brand);
        cv.put(COLUMN_YEAR,year);
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_QUANTITY,quantity);

       long result = db.update("partsList", cv,"_serialNumber=?",new String[]{row_serialNumber});
    if (result==-1){
        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
    }
    }
}
