package com.exmaple.todaycarddb2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PayCardDAO {
    @Insert
    public void insertPayCard(PayCard payCard);

    @Query("SELECT * FROM PayCard")
    List<PayCard> getAll();
}
