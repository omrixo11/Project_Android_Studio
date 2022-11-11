package com.esprit.lunar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);


        Spinner spinnerBrand = v.findViewById(R.id.spinnerCarBrand);
        Spinner spinnerModel = v.findViewById(R.id.spinnerCarModel);
        Spinner spinnerYear = v.findViewById(R.id.spinnerCarYear);


        ArrayAdapter<CharSequence> adapterBrand = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.carBrands, android.R.layout.simple_spinner_item);
        adapterBrand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterModel = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.carModel, android.R.layout.simple_spinner_item);
        adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.carYear, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerBrand.setAdapter(adapterBrand);
        spinnerBrand.setOnItemSelectedListener(this);

        spinnerModel.setAdapter(adapterModel);
        spinnerModel.setOnItemSelectedListener(this);

        spinnerYear.setAdapter(adapterYear);
        spinnerYear.setOnItemSelectedListener(this);

        return  v;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}