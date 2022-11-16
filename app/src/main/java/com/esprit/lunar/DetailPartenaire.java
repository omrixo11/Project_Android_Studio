package com.esprit.lunar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailPartenaire extends AppCompatActivity {

    EditText titre, nbrpieces;
    Button mod, sup;
    AppDataBase appDataBase;
    PartenaireDAO partenaireDAO;
    String id;
    Partenaire p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_partenaire);
        id = getIntent().getStringExtra("id");
        titre = findViewById(R.id.titre);
        nbrpieces = findViewById(R.id.nbrP);
        mod = findViewById(R.id.mod);
        sup = findViewById(R.id.supp);


        new Thread(() -> {


            accessDataBase();
            p = partenaireDAO.getPartenaire(Integer.parseInt(id));
            runOnUiThread(() -> {
                titre.setText(p.getTitre());
                nbrpieces.setText(p.getNbrPieces());
            });

        }).start();
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(() -> {

                    Partenaire pr = new Partenaire();
                    pr.set_id(Integer.parseInt(id));
                    pr.setTitre(titre.getText().toString());
                    pr.setNbrPieces(nbrpieces.getText().toString());

                    accessDataBase();
                    partenaireDAO.updatePartenaire(pr);

                    Intent i = new Intent(getApplicationContext(), ListPartenaires.class);

                    startActivity(i);
                }).start();

            }
        });

        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(() -> {

                    accessDataBase();
                    partenaireDAO.deletePartenaire(p);

                    Intent i = new Intent(getApplicationContext(), ListPartenaires.class);

                    startActivity(i);
                }).start();

            }
        });


    }

    public void accessDataBase() {
        appDataBase = AppDataBase.getAppDataBase(DetailPartenaire.this);
        partenaireDAO = appDataBase.partenaireDAO();

    }
}