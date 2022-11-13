package com.esprit.lunar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {


    EditText brand_input, year_input, name_input, serialNumber_input, quantity_input;
    Button update_button;
    String brand, year,name,serialNumber,quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

       brand_input = findViewById(R.id.CarBrand2);
       year_input = findViewById(R.id.CarYear2);
       name_input = findViewById(R.id.nomPiece2);
       serialNumber_input = findViewById(R.id.numPiece2);
       quantity_input = findViewById(R.id.quantity2);
       update_button = findViewById(R.id.update_button);
       getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DBHelper myDB = new DBHelper(UpdateActivity.this);
               myDB.updateData(brand, year, name, serialNumber, quantity);
           }
       });

    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("brand") && getIntent().hasExtra("year")
                && getIntent().hasExtra("name") && getIntent().hasExtra("serialNumber")
                && getIntent().hasExtra("quantity")) {
            //get
            brand = getIntent().getStringExtra("brand");
            year = getIntent().getStringExtra("year");
            name = getIntent().getStringExtra("name");
            serialNumber = getIntent().getStringExtra("serialNumber");
            quantity = getIntent().getStringExtra("quantity");

            //set
            brand_input.setText(brand);
            year_input.setText(year);
            name_input.setText(name);
            serialNumber_input.setText(serialNumber);
            quantity_input.setText(quantity);



        }else {
            Toast.makeText(this, "No Data",Toast.LENGTH_SHORT).show();
        }
    }
}