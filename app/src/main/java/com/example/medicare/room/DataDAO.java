package com.example.medicare.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertItem(AlarmModel item);
    @Delete
    void deleteItem(AlarmModel item);
    @Query("SELECT * FROM AlarmModel")
    LiveData<List<AlarmModel>> getAllData();
    @Query("DELETE FROM AlarmModel")
    void deleteAll();
    @Query("DELETE FROM AlarmModel WHERE id = :itemId")
    void deleteByItemId(int itemId);
    @Query("SELECT * FROM AlarmModel WHERE id = :itemId")
    AlarmModel getDataById(int itemId);
}
