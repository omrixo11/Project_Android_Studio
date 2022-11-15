package com.esprit.lunar;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PartenaireDAO {



    @Insert
    public void insertPartenaire(Partenaire partenaire);
    @Update
    public void updatePartenaire(Partenaire partenaire);
    @Delete
    public void  deletePartenaire (Partenaire partenaire);

    @Query("SELECT * FROM partenaire")
    public Cursor getAllPartenaire();

    @Query("SELECT * FROM partenaire where _id=:id")
    public Partenaire getPartenaire(int id);




}
