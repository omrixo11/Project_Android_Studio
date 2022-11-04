package com.esprit.lunar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button btnLogin;
    DBHelper DB;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences( "MyPreferences", MODE_PRIVATE);
        editor = preferences.edit();

        if (preferences.contains("saved_email")){

            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
        }
        else {

            email = findViewById(R.id.email);
            password = findViewById(R.id.password);
            btnLogin = findViewById(R.id.btnLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String my_email = email.getText().toString();
                    String my_password = password.getText().toString();
                    editor.putString("saved_email", my_email);
                    editor.putString("saved_password", my_password);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent);
                }
            });
        }
    }
}