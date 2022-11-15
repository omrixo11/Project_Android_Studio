package com.esprit.lunar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {


    EditText brand_input, year_input, name_input, serialNumber_input, quantity_input, price_input;
    Button update_button,delete_button;
    String id,brand, year,name,serialNumber,quantity,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


       brand_input = findViewById(R.id.CarBrand2);
       year_input = findViewById(R.id.CarYear2);
       name_input = findViewById(R.id.nomPiece2);
       serialNumber_input = findViewById(R.id.numPiece2);
       quantity_input = findViewById(R.id.quantity2);
       price_input = findViewById(R.id.price2);
       update_button = findViewById(R.id.update_button);
       delete_button = findViewById(R.id.delete_button);

       getAndSetIntentData();

       delete_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               confirmDialog();

           }
       });

        update_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DBHelper myDB = new DBHelper(UpdateActivity.this);
               brand = brand_input.getText().toString().trim();
               year = year_input.getText().toString().trim();
               name = name_input.getText().toString().trim();
               serialNumber = serialNumber_input.getText().toString().trim();
               quantity = quantity_input.getText().toString().trim();
               price = price_input.getText().toString().trim();
               myDB.updateData(id, brand, year, name, serialNumber, quantity, price);
           }
       });

    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("brand") && getIntent().hasExtra("year")
                && getIntent().hasExtra("name") && getIntent().hasExtra("serialNumber")
                && getIntent().hasExtra("quantity") && getIntent().hasExtra("price")) {

            //get
            id = getIntent().getStringExtra("id");
            brand = getIntent().getStringExtra("brand");
            year = getIntent().getStringExtra("year");
            name = getIntent().getStringExtra("name");
            serialNumber = getIntent().getStringExtra("serialNumber");
            quantity = getIntent().getStringExtra("quantity");
            price = getIntent().getStringExtra("price");

            //set
            brand_input.setText(brand);
            year_input.setText(year);
            name_input.setText(name);
            serialNumber_input.setText(serialNumber);
            quantity_input.setText(quantity);
            price_input.setText(price);
            Log.d("step", brand+" "+year+" "+name+" "+serialNumber+""+quantity+" "+price);

        }else {
            Toast.makeText(this, "No Data",Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + brand + "?");
        builder.setMessage("Are you sure you want to delete "+ brand + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DBHelper myDB = new DBHelper(UpdateActivity.this);
                myDB.deleteProduct(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

}