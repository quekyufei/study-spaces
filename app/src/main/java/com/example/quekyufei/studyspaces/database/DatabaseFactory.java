package com.example.quekyufei.studyspaces.database;

import com.example.quekyufei.studyspaces.database.localdatabase.LocalDatabase;

public class DatabaseFactory {
    public DatabaseInterface getDatabase(String type){
        if(type.equals("local")){
            return new LocalDatabase();
        }

        return null;
    }
}
