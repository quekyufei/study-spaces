package com.example.quekyufei.studyspaces.database.localdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.quekyufei.studyspaces.StudySpace;
import com.example.quekyufei.studyspaces.database.sqlAsset.AssetSQLiteOpenHelperFactory;

@Database(entities = {StudySpace.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudySpacesDAO ssDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,"studyspacesDB.db")
                    .openHelperFactory(new AssetSQLiteOpenHelperFactory())
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
