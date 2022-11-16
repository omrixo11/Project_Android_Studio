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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context2;
    private ArrayList brandc, yearc, namec, serialNumberc, quantityc, product_idc, pricec;
    Activity activity;

    CartAdapter(Activity activity, Context context2, ArrayList product_idc, ArrayList brandc, ArrayList yearc, ArrayList namec, ArrayList serialNumberc, ArrayList quantityc, ArrayList pricec) {

        this.activity = activity;
        this.context2 = context2;
        this.product_idc = product_idc;
        this.brandc = brandc;
        this.yearc = yearc;
        this.namec = namec;
        this.serialNumberc = serialNumberc;
        this.quantityc = quantityc;
        this.pricec = pricec;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context2);
        View view = inflater.inflate(R.layout.singleitemcard, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {

        holder.Product_idc.setText(String.valueOf(brandc.get(position)));
        holder.Brandc.setText(String.valueOf(brandc.get(position)));
        holder.Yearc.setText(String.valueOf(yearc.get(position)));
        holder.Namec.setText(String.valueOf(namec.get(position)));
        holder.SerialNumberc.setText(String.valueOf(serialNumberc.get(position)));
        holder.Quantityc.setText(String.valueOf(quantityc.get(position)));
        holder.Pricec.setText(String.valueOf(pricec.get(position)));

        holder.mainLayoutc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context2, UpdateCart.class);
                intent.putExtra("idc", String.valueOf(product_idc.get(position)));
                intent.putExtra("brandc", String.valueOf(brandc.get(position)));
                intent.putExtra("yearc", String.valueOf(yearc.get(position)));
                intent.putExtra("namec", String.valueOf(namec.get(position)));
                intent.putExtra("serialNumberc", String.valueOf(serialNumberc.get(position)));
                intent.putExtra("quantityc", String.valueOf(quantityc.get(position)));
                intent.putExtra("pricec", String.valueOf(pricec.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandc.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Product_idc, Brandc, Yearc, Namec, SerialNumberc, Quantityc, Pricec;
        LinearLayout mainLayoutc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Product_idc = itemView.findViewById(R.id.Product_idc);
            Brandc = itemView.findViewById(R.id.Brandc);
            Yearc = itemView.findViewById(R.id.Yearc);
            Namec = itemView.findViewById(R.id.Namec);
            SerialNumberc = itemView.findViewById(R.id.SerialNumberc);
            Quantityc = itemView.findViewById(R.id.Quantityc);
            Pricec = itemView.findViewById(R.id.Pricec);
            mainLayoutc = itemView.findViewById(R.id.mainLayoutc);
        }
    }


}
