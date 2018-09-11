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
    public CardView main_cardvw_1_1,main_cardvw_1_2, main_cardvw_2_1, main_cardvw_2_2, main_cardvw_3_1, main_cardvw_3_2;
    public Chronometer main_chr_1_1,main_chr_1_2,main_chr_2_1,main_chr_2_2,main_chr_3_1,main_chr_3_2;
    public int durum_1_1= 0 , durum_1_2 = 0, durum_2_1 = 0, durum_2_2 = 0 ,durum_3_1 = 0 , durum_3_2 = 0;
    private ImageButton footer_imgbtn_dashboard;
    private ImageButton footer_imgbtn_home;
    private ImageButton footer_imgbtn_profile;



    /*yorum*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // chronometers
        main_chr_1_1 = (Chronometer) findViewById(R.id.main_chr_1_1);
        main_chr_1_2 = (Chronometer) findViewById(R.id.main_chr_1_2);
        main_chr_2_1 = (Chronometer) findViewById(R.id.main_chr_2_1);
        main_chr_2_2 = (Chronometer) findViewById(R.id.main_chr_2_2);
        main_chr_3_1 = (Chronometer) findViewById(R.id.main_chr_3_1);
        main_chr_3_2 = (Chronometer) findViewById(R.id.main_chr_3_2);

        //cardview nesnelerinin tanımları
        main_cardvw_1_1= (CardView) findViewById(R.id.main_cardvw_1_1);
        main_cardvw_1_2 = (CardView) findViewById(R.id.main_cardvw_1_2);
        main_cardvw_2_1 = (CardView) findViewById(R.id.main_cardvw_2_1);
        main_cardvw_2_2 = (CardView) findViewById(R.id.main_cardvw_2_2);
        main_cardvw_3_1 = (CardView) findViewById(R.id.main_cardvw_3_1);
        main_cardvw_3_2 = (CardView) findViewById(R.id.main_cardvw_3_2);

        footer_imgbtn_dashboard = (ImageButton) findViewById(R.id.footer_imgbtn_dashboard);
        footer_imgbtn_home = (ImageButton) findViewById(R.id.footer_imgbtn_home);
        footer_imgbtn_profile = (ImageButton) findViewById(R.id.footer_imgbtn_profile);

        // toolbar icons on - off state
        footer_imgbtn_dashboard.setImageResource(R.drawable.main_dashboardbutton_off);
        footer_imgbtn_home.setImageResource(R.drawable.main_homebutton_on);
        footer_imgbtn_profile.setImageResource(R.drawable.main_profilebutton_off);





        //click listener to the cards
        main_cardvw_1_1.setOnClickListener(this);
        main_cardvw_1_2.setOnClickListener(this);
        main_cardvw_2_1.setOnClickListener(this);
        main_cardvw_2_2.setOnClickListener(this);
        main_cardvw_3_1.setOnClickListener(this);
        main_cardvw_3_2.setOnClickListener(this);


        footer_imgbtn_dashboard .setOnClickListener(this);
        footer_imgbtn_home .setOnClickListener(this);
        footer_imgbtn_profile .setOnClickListener(this);

        //chronometers invisible
        main_chr_1_1.setVisibility(View.INVISIBLE);
        main_chr_1_2.setVisibility(View.INVISIBLE);
        main_chr_2_1.setVisibility(View.INVISIBLE);
        main_chr_2_2.setVisibility(View.INVISIBLE);
        main_chr_3_1.setVisibility(View.INVISIBLE);
        main_chr_3_2.setVisibility(View.INVISIBLE);

    }

    public void resetChr()
    {
        main_chr_1_1.setBase(SystemClock.elapsedRealtime());
        main_chr_1_1.stop();
        main_chr_1_1.setVisibility(View.INVISIBLE);
        //
        main_chr_1_2.setBase(SystemClock.elapsedRealtime());
        main_chr_1_2.stop();
        main_chr_1_2.setVisibility(View.INVISIBLE);
        //
        main_chr_2_1.setBase(SystemClock.elapsedRealtime());
        main_chr_2_1.stop();
        main_chr_2_1.setVisibility(View.INVISIBLE);
        //
        main_chr_2_2.setBase(SystemClock.elapsedRealtime());
        main_chr_2_2.stop();
        main_chr_2_2.setVisibility(View.INVISIBLE);
        //
        main_chr_3_1.setBase(SystemClock.elapsedRealtime());
        main_chr_3_1.stop();
        main_chr_3_1.setVisibility(View.INVISIBLE);
        //
        main_chr_3_2.setBase(SystemClock.elapsedRealtime());
        main_chr_3_2.stop();
        main_chr_3_2.setVisibility(View.INVISIBLE);
        //durumlar
        durum_1_1 = 0;
        durum_1_2 = 0;
        durum_2_1 = 0;
        durum_2_2 = 0;
        durum_3_1 = 0;
        durum_3_2 = 0;
    }


    @Override
    public void onClick(View view) {


        switch (view.getId())
        {

            case R.id.main_cardvw_1_1 :
                    if( durum_1_1 == 0) {
                        resetChr();
                        main_chr_1_1.setBase(SystemClock.elapsedRealtime());
                        main_chr_1_1.start();
                        main_chr_1_1.setVisibility(View.VISIBLE);
                        durum_1_1 = 1;
                    }

                    else {
                        main_chr_1_1.setBase(SystemClock.elapsedRealtime());
                        main_chr_1_1.stop();
                        main_chr_1_1.setVisibility(View.INVISIBLE);
                        resetChr();
                    }



                break;

            case R.id.main_cardvw_1_2 :
                    if ( durum_1_2 == 0)
                    {
                        resetChr();
                        main_chr_1_2.setBase(SystemClock.elapsedRealtime());
                        main_chr_1_2.start();
                        main_chr_1_2.setVisibility(View.VISIBLE);
                        durum_1_2 = 1;
                    }
                    else {
                        main_chr_1_2.setBase(SystemClock.elapsedRealtime());
                        main_chr_1_2.stop();
                        main_chr_1_2.setVisibility(View.INVISIBLE);
                        resetChr();
                    }


                break;

            case R.id.main_cardvw_2_1 :
                if ( durum_2_1 == 0)
                {
                    resetChr();
                    main_chr_2_1.setBase(SystemClock.elapsedRealtime());
                    main_chr_2_1.start();
                    main_chr_2_1.setVisibility(View.VISIBLE);
                    durum_2_1 = 1;
                }

                else
                {
                    main_chr_2_1.setBase(SystemClock.elapsedRealtime());
                    main_chr_2_1.stop();
                    main_chr_2_1.setVisibility(View.INVISIBLE);
                    resetChr();
                }


                break;

            case R.id.main_cardvw_2_2 :
                if ( durum_2_2 == 0)
                {
                    resetChr();
                    main_chr_2_2.setBase(SystemClock.elapsedRealtime());
                    main_chr_2_2.start();
                    main_chr_2_2.setVisibility(View.VISIBLE);
                    durum_2_2=1;
                }

                else
                {
                    main_chr_2_2.setBase(SystemClock.elapsedRealtime());
                    main_chr_2_2.stop();
                    main_chr_2_2.setVisibility(View.INVISIBLE);
                    resetChr();
                }


                break;

            case R.id.main_cardvw_3_1 :
                if (durum_3_1 == 0)
                {
                    resetChr();
                    main_chr_3_1.setBase(SystemClock.elapsedRealtime());
                    main_chr_3_1.start();
                    main_chr_3_1.setVisibility(View.VISIBLE);
                    durum_3_1=1;
                }

                else
                {
                    main_chr_3_1.setBase(SystemClock.elapsedRealtime());
                    main_chr_3_1.stop();
                    main_chr_3_1.setVisibility(View.INVISIBLE);
                    resetChr();
                }


                break;

            case R.id.main_cardvw_3_2 :
                if (durum_3_2==0)
                {
                    resetChr();
                    main_chr_3_2.setBase(SystemClock.elapsedRealtime());
                    main_chr_3_2.start();
                    main_chr_3_2.setVisibility(View.VISIBLE);
                    durum_3_2=1;
                }

                else
                {
                    main_chr_3_2.setBase(SystemClock.elapsedRealtime());
                    main_chr_3_2.stop();
                    main_chr_3_2.setVisibility(View.INVISIBLE);
                    resetChr();
                }


                break;


            //bottom bar case
            case R.id.footer_imgbtn_dashboard :

                Intent dashboard=new Intent();
                dashboard.setClass(MainActivity.this,Dashboard.class);
                startActivity(dashboard);

                break;

            case R.id.footer_imgbtn_profile :

                Intent profile=new Intent();
                profile.setClass(MainActivity.this,Profile.class);
                startActivity(profile);

                break;

        }

    }


}
