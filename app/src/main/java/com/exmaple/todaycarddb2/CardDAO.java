package com.exmaple.todaycarddb2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDAO {
    @Insert
    public void insertCard(Card card);

    @Query("SELECT * FROM Card")
    List<Card> getAll();
}
