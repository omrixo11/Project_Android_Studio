package com.esprit.lunar;

import android.database.Cursor;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    DBHelper MyDB;

    ArrayList<String> brand, year, name, serialNumber, quantity, product_id, price;
    DisplayAdapter displayAdapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        swipeRefreshLayout = v.findViewById(R.id.swiperLayout);
        recyclerView = v.findViewById(R.id.recyclerView);
        MyDB = new DBHelper(getActivity());

        product_id = new ArrayList<>();
        brand = new ArrayList<>();
        year = new ArrayList<>();
        name = new ArrayList<>();
        serialNumber = new ArrayList<>();
        quantity = new ArrayList<>();
        price = new ArrayList<>();

        StoreDataInArrays();

        displayAdapter = new DisplayAdapter(getActivity(), getActivity(), product_id, brand, name, year, serialNumber, quantity, price);
        recyclerView.setAdapter(displayAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //////
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                displayAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //////


        return v;
    }

    void StoreDataInArrays() {
        Cursor cursor = MyDB.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                product_id.add(cursor.getString(0));
                brand.add(cursor.getString(1));
                year.add(cursor.getString(2));
                name.add(cursor.getString(3));
                serialNumber.add(cursor.getString(4));
                quantity.add(cursor.getString(5));
                price.add(cursor.getString(6));
            }
        }
    }

}