package com.example.quekyufei.studyspaces.database.localdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.quekyufei.studyspaces.StudySpace;

import java.util.List;

@Dao
public interface StudySpacesDAO {

    @Query("SELECT * FROM StudySpace")
    List<StudySpace> getAllSpaces();

    @Query("SELECT * FROM StudySpace WHERE id = :id")
    StudySpace getSpaceByID(int id);

}
