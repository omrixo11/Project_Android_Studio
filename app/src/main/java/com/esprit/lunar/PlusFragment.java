package com.esprit.lunar;

import static com.esprit.lunar.DBHelper.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class PlusFragment extends Fragment {

    int id = 0;
    SQLiteDatabase sqLiteDatabase;
    DBHelper MyDB;
    Button btnAdd;
    EditText brand;
    EditText year;
    EditText name;
    EditText serialNumber;
    EditText quantity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_plus, container, false);


        brand = v.findViewById(R.id.CarBrand);
        year = v.findViewById(R.id.CarYear);
        name = v.findViewById(R.id.nomPiece);
        serialNumber = v.findViewById(R.id.numPiece);
        quantity = v.findViewById(R.id.quantity);
        btnAdd = v.findViewById(R.id.addtostock);
        MyDB = new DBHelper(getActivity());


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper myDB = new DBHelper(getActivity());
                if (name.length() == 0 || brand.length() == 0 || year.length() == 0 || serialNumber.length() == 0 || quantity.length() == 0) {
                    Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    myDB.addProduct(brand.getText().toString().trim(),
                            Integer.valueOf(year.getText().toString().trim()),
                            name.getText().toString().trim(),
                            Integer.valueOf(serialNumber.getText().toString().trim()),
                            Integer.valueOf(quantity.getText().toString().trim()));
                    clear();

                }
            }

        });
        return v;
    }

    private void clear() {
        brand.setText("");
        year.setText("");
        name.setText("");
        serialNumber.setText("");
        quantity.setText("");
    }

}