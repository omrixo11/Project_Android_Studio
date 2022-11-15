package com.esprit.lunar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListPartenaires extends AppCompatActivity {

ListView ls;
    PartenaireDAO partenaireDAO;
    AppDataBase appDataBase;
   @Override
    protected void onCreate(Bundle savedInstanceState)
   {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_list_partenaires);

       ls=findViewById(R.id.list);
       new Thread(()->{
           accessDataBase();
           Cursor c=partenaireDAO.getAllPartenaire();


           runOnUiThread(()->{
               SimpleCursorAdapter adapter =new SimpleCursorAdapter(ListPartenaires.this, R.layout.item,c,
                       new String[]{c.getColumnName(0),c.getColumnName(1),c.getColumnName(2)},
                       new int []{R.id.idd, R.id.titree, R.id.nbrPieces},1);


               ls.setAdapter(adapter);
               ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                       TextView t= view.findViewById(R.id.idd);
                       Intent i=new Intent(getApplicationContext(),DetailPartenaire.class);
                       i.putExtra("id",t.getText().toString());
                       startActivity(i);
                   }
               });

           });
   }).start();

   }
    public void accessDataBase()
    {
         appDataBase = AppDataBase.getAppDataBase(ListPartenaires.this);
         partenaireDAO = appDataBase.partenaireDAO();

    }
}
