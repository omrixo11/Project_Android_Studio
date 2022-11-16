package com.esprit.lunar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.MyViewHolder> {

    private Context context;
    private ArrayList brand, year, name, serialNumber, quantity, product_id, price;
    Activity activity;

    DisplayAdapter(Activity activity, Context context, ArrayList product_id, ArrayList brand, ArrayList year, ArrayList name, ArrayList serialNumber, ArrayList quantity, ArrayList price) {

        this.activity = activity;
        this.context = context;
        this.product_id = product_id;
        this.brand = brand;
        this.year = year;
        this.name = name;
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.price = price;
    }

    @NonNull
    @Override
    public DisplayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledata, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayAdapter.MyViewHolder holder, int position) {

        holder.Product_id.setText(String.valueOf(brand.get(position)));
        holder.Brand.setText(String.valueOf(brand.get(position)));
        holder.Year.setText(String.valueOf(year.get(position)));
        holder.Name.setText(String.valueOf(name.get(position)));
        holder.SerialNumber.setText(String.valueOf(serialNumber.get(position)));
        holder.Quantity.setText(String.valueOf(quantity.get(position)));
        holder.Price.setText(String.valueOf(price.get(position)));



        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(product_id.get(position)));
                intent.putExtra("brand", String.valueOf(brand.get(position)));
                intent.putExtra("year", String.valueOf(year.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("serialNumber", String.valueOf(serialNumber.get(position)));
                intent.putExtra("quantity", String.valueOf(quantity.get(position)));
                intent.putExtra("price", String.valueOf(price.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brand.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Product_id, Brand, Year, Name, SerialNumber, Quantity, Price;
        LinearLayout mainLayout;
        Button btnCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Product_id = itemView.findViewById(R.id.Product_id);
            Brand = itemView.findViewById(R.id.Brand);
            Year = itemView.findViewById(R.id.Year);
            Name = itemView.findViewById(R.id.Name);
            SerialNumber = itemView.findViewById(R.id.SerialNumber);
            Quantity = itemView.findViewById(R.id.Quantity);
            Price = itemView.findViewById(R.id.Price);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }


}
