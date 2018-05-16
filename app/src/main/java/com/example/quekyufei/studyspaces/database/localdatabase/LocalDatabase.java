package com.example.quekyufei.studyspaces.database.localdatabase;

import android.content.Context;

import com.example.quekyufei.studyspaces.StudySpace;
import com.example.quekyufei.studyspaces.database.DatabaseInterface;

import java.util.List;

public class LocalDatabase implements DatabaseInterface {

    public List<StudySpace> getStudySpaces(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        List<StudySpace> spaceList = db.ssDao().getAllSpaces();
        return spaceList;
    }
}
