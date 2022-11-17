package com.esprit.lunar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCart extends AppCompatActivity {

    EditText brand_inputc, year_inputc, name_inputc, serialNumber_inputc, quantity_inputc, price_inputc;
    Button update_button3, delete_button3;
    String idc, brandc, yearc, namec, serialNumberc, quantityc, pricec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cart);

        brand_inputc = findViewById(R.id.CarBrand3);
        year_inputc = findViewById(R.id.CarYear3);
        name_inputc = findViewById(R.id.nomPiece3);
        serialNumber_inputc = findViewById(R.id.numPiece3);
        quantity_inputc = findViewById(R.id.quantity3);
        price_inputc = findViewById(R.id.price3);



        update_button3 = findViewById(R.id.update_button3);
        delete_button3 = findViewById(R.id.delete_button3);
        getAndSetIntentData();

        delete_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();

            }
        });

        update_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper myDB = new DBHelper(getBaseContext());
                brandc = brand_inputc.getText().toString().trim();
                yearc = year_inputc.getText().toString().trim();
                namec = name_inputc.getText().toString().trim();
                serialNumberc = serialNumber_inputc.getText().toString().trim();
                quantityc = quantity_inputc.getText().toString().trim();
                pricec = price_inputc.getText().toString().trim();
                myDB.updateDataCard(idc, brandc, yearc, namec, serialNumberc, quantityc, pricec);
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("idc") && getIntent().hasExtra("brandc") && getIntent().hasExtra("yearc")
                && getIntent().hasExtra("namec") && getIntent().hasExtra("serialNumberc")
                && getIntent().hasExtra("quantityc") && getIntent().hasExtra("pricec")) {

            //get
            idc = getIntent().getStringExtra("idc");
            brandc = getIntent().getStringExtra("brandc");
            yearc = getIntent().getStringExtra("yearc");
            namec = getIntent().getStringExtra("namec");
            serialNumberc = getIntent().getStringExtra("serialNumberc");
            quantityc = getIntent().getStringExtra("quantityc");
            pricec = getIntent().getStringExtra("pricec");

            //set
            brand_inputc.setText(brandc);
            year_inputc.setText(yearc);
            name_inputc.setText(namec);
            serialNumber_inputc.setText(serialNumberc);
            quantity_inputc.setText(quantityc);
            price_inputc.setText(pricec);
            Log.d("step", brandc + " " + yearc + " " + namec + " " + serialNumberc + "" + quantityc + " " + pricec);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + brandc + "?");
        builder.setMessage("Are you sure you want to delete " + brandc + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DBHelper myDB = new DBHelper(getBaseContext());
                myDB.deleteCart(idc);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
}