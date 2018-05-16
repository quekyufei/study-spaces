package com.example.quekyufei.studyspaces.database.localdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.quekyufei.studyspaces.StudySpace;

import java.util.List;

@Dao
public interface StudySpacesDAO {

    @Query("SELECT * FROM studyspace")
    List<StudySpace> getAllSpaces();

}
