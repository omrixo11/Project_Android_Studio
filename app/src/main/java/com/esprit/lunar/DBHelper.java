package com.esprit.lunar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DBNAME = "MyDB.db";
    private static final int DATABASE_VERSION = 1;

    static final String TABLE_NAME = "partsList";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BRAND = "brand";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SERIALNUMBER = "serialNumber";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PRICE = "price";

    static final String TABLE_CART = "cart";
    private static final String COLUMN_IDC = "_idc";
    private static final String COLUMN_BRANDC = "brandc";
    private static final String COLUMN_YEARC = "yearc";
    private static final String COLUMN_NAMEC = "namec";
    private static final String COLUMN_SERIALNUMBERC = "serialNumberc";
    private static final String COLUMN_QUANTITYC = "quantityc";
    private static final String COLUMN_PRICEC = "pricec";

    DBHelper(Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (emaill TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table my_library(COLUMN_TITLE TEXT primary key, COLUMN_DESC TEXT, COLUMN_NBR INTEGER)");
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BRAND + " TEXT, " +
                COLUMN_YEAR + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SERIALNUMBER + " INTEGER, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_PRICE + " INTEGER);";
        MyDB.execSQL(query);

        String query2 = "CREATE TABLE " + TABLE_CART +
                " (" + COLUMN_IDC + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BRANDC + " TEXT, " +
                COLUMN_YEARC + " INTEGER, " +
                COLUMN_NAMEC + " TEXT, " +
                COLUMN_SERIALNUMBERC + " INTEGER, " +
                COLUMN_QUANTITYC + " INTEGER, " +
                COLUMN_PRICEC + " INTEGER);";
        MyDB.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(MyDB);
    }

    public Boolean insertData(String emaill, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emaill", emaill);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
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

    public Boolean checkusernamepassword(String emaill, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where emaill = ? and password = ?", new String[]{emaill, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor getData () {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from partsList", null);
        return cursor;
    }

    public Cursor getDataCart () {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor2 = MyDB.rawQuery("Select * from cart", null);
        return cursor2;
    }

    void addProduct(String brand, int year, String name, int serialNumber, int quantity, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRAND, brand);
        cv.put(COLUMN_YEAR, year);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SERIALNUMBER, serialNumber);
        cv.put(COLUMN_QUANTITY, quantity);
        cv.put(COLUMN_PRICE, price);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void addToCart(String brandc, int yearc, String namec, int serialNumberc, int quantityc, int pricec) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRANDC, brandc);
        cv.put(COLUMN_YEARC, yearc);
        cv.put(COLUMN_NAMEC, namec);
        cv.put(COLUMN_SERIALNUMBERC, serialNumberc);
        cv.put(COLUMN_QUANTITYC, quantityc);
        cv.put(COLUMN_PRICEC, pricec);

        long result = db.insert(TABLE_CART, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added to cart!", Toast.LENGTH_SHORT).show();
        }
    }

    void updateData(String row_id, String brand, String year, String name, String serialNumber, String quantity, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRAND, brand);
        cv.put(COLUMN_YEAR, year);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SERIALNUMBER, serialNumber);
        cv.put(COLUMN_QUANTITY, quantity);
        cv.put(COLUMN_PRICE, price);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void updateDataCard(String row_idc, String brandc, String yearc, String namec, String serialNumberc, String quantityc, String pricec) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRANDC, brandc);
        cv.put(COLUMN_YEARC, yearc);
        cv.put(COLUMN_NAMEC, namec);
        cv.put(COLUMN_SERIALNUMBERC, serialNumberc);
        cv.put(COLUMN_QUANTITYC, quantityc);
        cv.put(COLUMN_PRICEC, pricec);

        long result = db.update(TABLE_CART, cv, "_idc=?", new String[]{row_idc});
        if (result == -1) {
            Toast.makeText(context, "Failed to update cart", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Cart pdated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProduct(String row_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteCart(String row_idc) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_CART, "_idc=?", new String[]{row_idc});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted from basket", Toast.LENGTH_SHORT).show();
        }
    }


    //////Loujain////////

    void addPartenaire(String title, String description, int nbrPieces) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("COLUMN_TITLE", title);
        cv.put("COLUMN_DESC", description);
        cv.put("COLUMN_NBR", nbrPieces);
        long result = db.insert("my_library", null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor getDataa() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from my_library", null);
        return cursor;
    }

    Cursor readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM my_library";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}

