package com.esprit.lunar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class PartenaireActivity extends AppCompatActivity {

    EditText titre,nbPieces;
    Button add;
    AppDataBase appDataBase;
    PartenaireDAO partenaireDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partenaire);

        titre=findViewById(R.id.titleeee);
        nbPieces=findViewById(R.id.nbrPieces);
        add=findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(

                        ()->{
                            accessDataBase();
                            Partenaire p=new Partenaire();
                            p.setTitre(titre.getText().toString());
                            p.setNbrPieces(nbPieces.getText().toString());
                            partenaireDAO.insertPartenaire(p);
                            Intent i=new Intent(getApplicationContext(),ListPartenaires.class);
                            startActivity(i);


                        }

                ).start();



            }
        });


    }

    public void accessDataBase()
    {
         appDataBase=AppDataBase.getAppDataBase(PartenaireActivity.this);
        partenaireDAO=appDataBase.partenaireDAO();

    }



    }
