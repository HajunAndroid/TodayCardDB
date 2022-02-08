package com.exmaple.todaycarddb2;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Card.class, parentColumns = "id",childColumns = "card_id",onDelete = CASCADE),
        @ForeignKey(entity = DailySpend.class, parentColumns = "created", childColumns = "created", onDelete = CASCADE)})
public class PayCard {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private String created;

    private int price;

    private String place;

    private String permit;

    private String card_id;

    public PayCard(String created, int price, String place, String permit, String card_id) {
        this.created = created;
        this.price = price;
        this.place = place;
        this.permit = permit;
        this.card_id = card_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }
}
