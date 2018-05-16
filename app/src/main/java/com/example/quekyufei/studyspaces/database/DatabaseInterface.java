package com.example.quekyufei.studyspaces.database;

import android.content.Context;

import com.example.quekyufei.studyspaces.StudySpace;

import java.util.List;

public interface DatabaseInterface {
    List<StudySpace> getStudySpaces(Context context);
}
