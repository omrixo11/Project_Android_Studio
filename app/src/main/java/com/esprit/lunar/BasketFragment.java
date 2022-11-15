package com.esprit.lunar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BasketFragment extends Fragment {

    String brand,year,name,serialNumber,quantity,price;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_basket, container, false);

        this.brand = brand;
        this.year = year;
        this.name = name;
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.price = price;



       return v;
    }
}