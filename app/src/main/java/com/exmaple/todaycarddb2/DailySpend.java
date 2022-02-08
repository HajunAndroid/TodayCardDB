package com.exmaple.todaycarddb2;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DailySpend {
    @PrimaryKey
    @NonNull
    private String created;

    private int total_card;

    private int total_cash;

    public DailySpend(@NonNull String created, int total_card, int total_cash) {
        this.created = created;
        this.total_card = total_card;
        this.total_cash = total_cash;
    }

    @NonNull
    public String getCreated() {
        return created;
    }

    public void setCreated(@NonNull String created) {
        this.created = created;
    }

    public int getTotal_card() {
        return total_card;
    }

    public void setTotal_card(int total_card) {
        this.total_card = total_card;
    }

    public int getTotal_cash() {
        return total_cash;
    }

    public void setTotal_cash(int total_cash) {
        this.total_cash = total_cash;
    }
}
