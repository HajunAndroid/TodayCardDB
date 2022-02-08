package com.exmaple.todaycarddb2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    CardDAO cDao;
    DailySpendDAO dDao;
    PayCashDAO pDao;
    PayCardDAO ppDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "testDB").build();
        cDao = db.cardDAO();
        dDao = db.dailySpendDAO();
        pDao = db.payCashDAO();
        ppDao = db.payCardDAO();
        new SelectAllThread().start();
    }
    class SelectAllThread extends Thread{
        @Override
        public void run() {
            List<Card> cardList = cDao.getAll();
            for(Card c:cardList){
                Log.d("yang",c.getCompany()+" "+c.getId()+" ");
            }

            List<DailySpend> dailySpendList = dDao.getAll();
            for(DailySpend d:dailySpendList){
                Log.d("yang",d.getCreated()+" "+d.getTotal_card()+" "+d.getTotal_cash());
            }

            int cash = 0;
            List<PayCash> payCashList = pDao.getAll();
            for(PayCash p:payCashList){
                cash+=p.getPrice();
                Log.d("yang",p.getId()+" "+p.getCreated()+" "+p.getPrice()+" "+p.getPlace()+" "+p.getPermit());
            }

            int card = 0;
            List<PayCard> payCardList = ppDao.getAll();
            for(PayCard p:payCardList){
                card+=p.getPrice();
                Log.d("yang",p.getId()+" "+p.getCreated()+" "+p.getPrice()+" "+p.getPlace()+" "+p.getPermit()+" "+p.getCard_id());
            }

            dDao.sum(cash,card,"2022-02-08");
            dailySpendList = dDao.getAll();
            for(DailySpend d:dailySpendList){
                Log.d("yang",d.getCreated()+" "+d.getTotal_card()+" "+d.getTotal_cash());
            }
        }
    }
}