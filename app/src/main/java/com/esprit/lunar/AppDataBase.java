package com.esprit.lunar;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Partenaire.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;
    public abstract PartenaireDAO partenaireDAO();
    public static AppDataBase getAppDataBase(Context context)

    {
        if(INSTANCE ==null)
        {
            synchronized (AppDataBase.class )
            {
                if (INSTANCE==null)
                {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,"PartenaireManager").build();
                }
            }
        }

        return INSTANCE;

    }




}
