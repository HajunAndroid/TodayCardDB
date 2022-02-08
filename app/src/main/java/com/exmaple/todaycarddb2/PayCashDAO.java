package com.exmaple.todaycarddb2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PayCashDAO {
    @Insert
    public void insertPayCash(PayCash payCash);

    @Query("SELECT * FROM PayCash")
    List<PayCash> getAll();

}
