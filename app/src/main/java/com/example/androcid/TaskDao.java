package com.example.androcid;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import io.reactivex.Single;



@Dao
public interface TaskDao {

    @Query("SELECT * from task ORDER BY id DESC ")
    Single<List<Task>> getAll();

    @Query("SELECT * from task ORDER BY id DESC limit 1")
    List<Task> getLatest();

    @Query("SELECT COUNT(*) from task")
    int countUsers();

    @Insert
    void insert(Task task);



}
