package com.exmaple.todaycarddb2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardDAO cDao;
    DailySpendDAO dDao;
    PayCashDAO pDao;
    PayCardDAO ppDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "testDB").build();
        cDao = db.cardDAO();
        dDao = db.dailySpendDAO();
        pDao = db.payCashDAO();
        ppDao = db.payCardDAO();
        PayCash payCash = new PayCash("2022-02-08",4000,"CU","사용");
        PayCard payCard = new PayCard("2022-02-08",2000,"GS25","사용","9*6*");
        Card card = new Card("9*6*","KB국민카드");
        DailySpend dailySpend = new DailySpend("2022-02-08",0,0);
        new InsertThread(card,dailySpend,payCash,payCard).start();
    }
    class InsertThread extends Thread{
        Card card;
        DailySpend dailySpend;
        PayCash payCash;
        PayCard payCard;
        public InsertThread(Card card, DailySpend dailySpend, PayCash payCash,
                            PayCard payCard){
            this.card = card;
            this.dailySpend = dailySpend;
            this.payCash = payCash;
            this.payCard = payCard;
        }

        @Override
        public void run() {
            cDao.insertCard(card);
            dDao.insertDailySpend(dailySpend);
            pDao.insertPayCash(payCash);
            ppDao.insertPayCard(payCard);
        }
    }
    public void btn(View view){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}