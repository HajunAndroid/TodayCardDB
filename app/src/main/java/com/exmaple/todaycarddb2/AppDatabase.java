package com.exmaple.todaycarddb2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Card.class, DailySpend.class, PayCash.class, PayCard.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CardDAO cardDAO();
    public abstract DailySpendDAO dailySpendDAO();
    public abstract PayCashDAO payCashDAO();
    public abstract PayCardDAO payCardDAO();
}
