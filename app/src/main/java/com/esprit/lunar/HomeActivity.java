package com.esprit.lunar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button buttonn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonn = (Button) findViewById(R.id.signup2);
        buttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignIn();
            }
        });

    }
    private void openSignIn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}