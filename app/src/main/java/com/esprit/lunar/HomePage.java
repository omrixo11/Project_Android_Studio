package com.esprit.lunar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.esprit.lunar.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;

public class HomePage extends AppCompatActivity {


    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        navigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.search:
                        fragment = new SearchFragment();
                        break;

                    case R.id.basket:
                        fragment = new BasketFragment();
                        break;

                    case R.id.plus:
                        fragment = new PlusFragment();
                        break;

                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return true;
            }
        });
    }
}