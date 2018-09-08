package com.app.cronia.cronia10;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView card_food,card_read;
    private int Cardview_ID;
    private Chronometer mChronometer_food;
    private int durum= 0;
    private String deneme;
    private ImageButton Footer_ImgBtn_Dashboard;



    /*yorum*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // chronometers
        mChronometer_food = (Chronometer) findViewById(R.id.mChronometer_food);

        //cardview nesnelerinin tanımları
        card_food= (CardView) findViewById(R.id.card_food);
        card_read = (CardView) findViewById(R.id.card_read);
        Footer_ImgBtn_Dashboard = (ImageButton) findViewById(R.id.Footer_ImgBtn_Dashboard);


        //click listener to the cards
        card_food.setOnClickListener(this);
        card_read.setOnClickListener(this);
        Footer_ImgBtn_Dashboard .setOnClickListener(this);

        //chronometers invisible
        mChronometer_food.setVisibility(View.INVISIBLE);




    }





    @Override
    public void onClick(View view) {


        switch (view.getId())
        {


            case R.id.card_food :

                if ( durum == 0)
                {
                    mChronometer_food.setBase(SystemClock.elapsedRealtime());
                    mChronometer_food.start();
                    mChronometer_food.setVisibility(View.VISIBLE);
                    durum = 1;
                }

                else
                {
                    mChronometer_food.setBase(SystemClock.elapsedRealtime());
                    mChronometer_food.stop();
                    mChronometer_food.setVisibility(View.INVISIBLE);
                    durum = 0;

                }

                break;


            case R.id.Footer_ImgBtn_Dashboard :

                Intent i=new Intent();
                i.setClass(MainActivity.this,Dashboard.class);
                startActivity(i);


        }



    }





}
