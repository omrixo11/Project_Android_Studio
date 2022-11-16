package com.esprit.lunar;

import static android.content.Intent.getIntent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class BasketFragment extends Fragment {

    DBHelper MyDB;
    ArrayList<String> brandc, yearc, namec, serialNumberc, quantityc, product_idc, pricec;
    CartAdapter cartAdapter;
    RecyclerView recyclerViewc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_basket, container, false);


        recyclerViewc = v.findViewById(R.id.recyclerViewc);
        MyDB = new DBHelper(getActivity());

        product_idc = new ArrayList<>();
        brandc = new ArrayList<>();
        yearc = new ArrayList<>();
        namec = new ArrayList<>();
        serialNumberc = new ArrayList<>();
        quantityc = new ArrayList<>();
        pricec = new ArrayList<>();

        StoreDataInArrays();

        cartAdapter = new CartAdapter(getActivity(), getActivity(), product_idc, brandc, namec, yearc, serialNumberc, quantityc, pricec);
        recyclerViewc.setAdapter(cartAdapter);
        recyclerViewc.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

   private void StoreDataInArrays() {
        Cursor cursor2 = MyDB.getDataCart();
        if (cursor2.getCount() == 0) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor2.moveToNext()) {
                product_idc.add(cursor2.getString(0));
                brandc.add(cursor2.getString(1));
                yearc.add(cursor2.getString(2));
                namec.add(cursor2.getString(3));
                serialNumberc.add(cursor2.getString(4));
                quantityc.add(cursor2.getString(5));
                pricec.add(cursor2.getString(6));
            }
        }
    }
}