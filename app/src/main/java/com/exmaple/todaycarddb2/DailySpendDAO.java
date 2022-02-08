package com.exmaple.todaycarddb2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DailySpendDAO {
    @Insert
    public void insertDailySpend(DailySpend dailySpend);

    @Query("SELECT * FROM DailySpend")
    List<DailySpend> getAll();

    @Query("SELECT * FROM DAILYSPEND LEFT JOIN PAYCASH ON DAILYSPEND.created = PAYCASH.created " +
            "WHERE PAYCASH.created = :value")
    List<DailySpend> getSameCreated(String value);

    @Query("UPDATE DailySpend SET total_card =:value1, total_cash =:value2 Where created =:created")
    public void sum(int value1, int value2, String created);
}
