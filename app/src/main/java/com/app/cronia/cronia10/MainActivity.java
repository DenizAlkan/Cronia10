package com.app.cronia.cronia10;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.app.cronia.cronia10.Database.DatabaseHelper;
import com.app.cronia.cronia10.Graphic.ClockPieHelper;
import com.app.cronia.cronia10.Graphic.ClockPieView;

import java.util.ArrayList;

import static android.os.SystemClock.*;
import static com.app.cronia.cronia10.R.drawable.login_logo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public CardView main_cardvw_1_1,main_cardvw_1_2, main_cardvw_2_1, main_cardvw_2_2, main_cardvw_3_1, main_cardvw_3_2;
    public Chronometer main_chr_1_1,main_chr_1_2,main_chr_2_1,main_chr_2_2,main_chr_3_1,main_chr_3_2;
    public ImageButton main_btn_1_1,main_btn_1_2,main_btn_2_1,main_btn_2_2,main_btn_3_1,main_btn_3_2;
    public TextView main_txt_1_1,main_txt_1_2,main_txt_2_1,main_txt_2_2,main_txt_3_1,main_txt_3_2;
    public int durum_1_1= 0 , durum_1_2 = 0, durum_2_1 = 0, durum_2_2 = 0 ,durum_3_1 = 0 , durum_3_2 = 0;
    public int etklinliksayisi=0;
    private ImageButton footer_imgbtn_dashboard;
    private ImageButton footer_imgbtn_home;
    private ImageButton footer_imgbtn_profile;

    ClockPieView clockPieView;

    //Notification
    private static final int NOTIFICATION_ID = 1;
    private CharSequence notificationTitleText = "Hello Title";
    private CharSequence notificationDescText = "This is custom Notification desc";







    /*yorum*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clockPieView = (ClockPieView)findViewById(R.id.clock_pie_view);
        set(clockPieView);

        //notification











        //statusbar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_rect_color));



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

        //cardviews image buttons tanımlamaları
        main_btn_1_1 = (ImageButton) findViewById(R.id.main_btn_1_1);
        main_btn_1_2 = (ImageButton) findViewById(R.id.main_btn_1_2);
        main_btn_2_1 = (ImageButton) findViewById(R.id.main_btn_2_1);
        main_btn_2_2 = (ImageButton) findViewById(R.id.main_btn_2_2);
        main_btn_3_1 = (ImageButton) findViewById(R.id.main_btn_3_1);
        main_btn_3_2 = (ImageButton) findViewById(R.id.main_btn_3_2);

        footer_imgbtn_dashboard = (ImageButton) findViewById(R.id.footer_imgbtn_dashboard);
        footer_imgbtn_home = (ImageButton) findViewById(R.id.footer_imgbtn_home);
        footer_imgbtn_profile = (ImageButton) findViewById(R.id.footer_imgbtn_profile);

        // toolbar icons on - off state
        footer_imgbtn_dashboard.setImageResource(R.drawable.main_dashboardbutton_off);
        footer_imgbtn_home.setImageResource(R.drawable.main_homebutton_on);
        footer_imgbtn_profile.setImageResource(R.drawable.main_profilebutton_off);

        // main textviews
        main_txt_1_1 = (TextView) findViewById(R.id.main_txt_1_1);
        main_txt_1_2 = (TextView) findViewById(R.id.main_txt_1_2);
        main_txt_2_1 = (TextView) findViewById(R.id.main_txt_2_1);
        main_txt_2_2 = (TextView) findViewById(R.id.main_txt_2_2);
        main_txt_3_1 = (TextView) findViewById(R.id.main_txt_3_1);
        main_txt_3_2 = (TextView) findViewById(R.id.main_txt_3_2);
        ColorStateList oldColors =    main_txt_1_1.getTextColors();


        //click listener to the cards
        main_cardvw_1_1.setOnClickListener(this);
        main_cardvw_1_2.setOnClickListener(this);
        main_cardvw_2_1.setOnClickListener(this);
        main_cardvw_2_2.setOnClickListener(this);
        main_cardvw_3_1.setOnClickListener(this);
        main_cardvw_3_2.setOnClickListener(this);

        //click listener chr
        main_chr_1_1.setOnClickListener(this);

        //click listener image buttons
        main_btn_1_1.setOnClickListener(this);


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

    public void randomSet(ClockPieView clockPieView){
        ArrayList<ClockPieHelper> clockPieHelperArrayList = new ArrayList<ClockPieHelper>();
        for(int i=0; i<20; i++){
            int startHour = (int)(24*Math.random());
            int startMin = (int)(60*Math.random());
            int duration = (int)(50*Math.random());
            clockPieHelperArrayList.add(new ClockPieHelper(startHour,startMin,0,startHour,startMin+duration,0));
        }
        clockPieView.setDate(clockPieHelperArrayList);
    }

    public void set(ClockPieView clockPieView){
        ArrayList<ClockPieHelper> clockPieHelperArrayList = new ArrayList<ClockPieHelper>();
        clockPieHelperArrayList.add(new ClockPieHelper(1,50,2,30));
        clockPieHelperArrayList.add(new ClockPieHelper(5,50,6,30));
        clockPieHelperArrayList.add(new ClockPieHelper(8,50,10,30));
        clockPieHelperArrayList.add(new ClockPieHelper(10,50,12 ,30));
        clockPieHelperArrayList.add(new ClockPieHelper(6,50,8,30));
        clockPieView.setDate(clockPieHelperArrayList);
    }


    private void setMyCustomNotification()
    {
        //When the user clicks the button this intent will trigger
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

        //Inflating our custom layout by the RemoteViews class
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.main_notification_normal);
        //Setting custom layout views properties
        remoteViews.setImageViewResource(R.id.imageViewIcon, R.drawable.main_ent_eatingicon);
        remoteViews.setTextViewText(R.id.textViewTitle, notificationTitleText);
        remoteViews.setTextViewText(R.id.textViewDesc, notificationDescText);

        //Create a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        //Setting small icon for our notification
        builder.setSmallIcon(R.drawable.login_logo);
        //Attaching our custom notification views to notification
        builder.setContent(remoteViews);
        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        //Attaching pending intent to notification
        builder.setContentIntent(pendingIntent);

        //And finally NotificationManager for the notify the user!
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }



    @Override
    public void onClick(View view) {
        final DatabaseHelper mdb = new DatabaseHelper(this);
        mdb.getWritableDatabase();

        switch (view.getId())
        {

            case R.id.main_cardvw_1_1  :

                if ( etklinliksayisi <3 || durum_1_1 == 1)
                {
                    if( durum_1_1 == 0) {

                        main_chr_1_1.setBase(elapsedRealtime());
                        mdb.addData(1,1);
                        main_chr_1_1.start();
                        main_chr_1_1.setVisibility(View.VISIBLE);
                        main_cardvw_1_1.setCardBackgroundColor(getResources().getColor(R.color.main_cardvw_1_1));
                        main_btn_1_1.setImageResource(R.drawable.main_ent_eatingicon_on);
                        main_txt_1_1.setTextColor(getResources().getColor(R.color.white));
                        durum_1_1 = 1;
                        etklinliksayisi++;

                        setMyCustomNotification();





                    }

                    else {
                        main_chr_1_1.setBase(elapsedRealtime());
                        mdb.updateFinishDate("Yemek");
                        main_chr_1_1.stop();
                        main_chr_1_1.setVisibility(View.INVISIBLE);
                        etklinliksayisi--;
                        durum_1_1 = 0;
                        main_cardvw_1_1.setCardBackgroundColor(getResources().getColor(R.color.white));
                        main_btn_1_1.setImageResource(R.drawable.main_ent_eatingicon);
                        main_txt_1_1.setTextColor(getResources().getColor(R.color.main_text_color));

                    }
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Aynı anda 3'den fazla etkinlik çalıştıramazsın ne yazik ki :(", Toast.LENGTH_LONG).show();
                }






                break;

            case R.id.main_cardvw_1_2 :

                if ( etklinliksayisi <3 || durum_1_2 == 1)
                {
                    if ( durum_1_2 == 0)
                    {

                        main_chr_1_2.setBase(elapsedRealtime());
                        mdb.addData(2,1);
                        main_chr_1_2.start();
                        main_chr_1_2.setVisibility(View.VISIBLE);
                        main_cardvw_1_2.setCardBackgroundColor(getResources().getColor(R.color.main_cardvw_1_2));
                        main_btn_1_2.setImageResource(R.drawable.main_ent_readingicon_on);
                        main_txt_1_2.setTextColor(getResources().getColor(R.color.white));
                        durum_1_2 = 1;
                        etklinliksayisi++;
                    }
                    else {
                        main_chr_1_2.setBase(elapsedRealtime());
                        mdb.updateFinishDate("Kitap Okuma");
                        main_chr_1_2.stop();
                        main_chr_1_2.setVisibility(View.INVISIBLE);
                        etklinliksayisi--;
                        durum_1_2 = 0;
                        main_cardvw_1_2.setCardBackgroundColor(getResources().getColor(R.color.white));
                        main_btn_1_2.setImageResource(R.drawable.main_ent_readingicon);
                        main_txt_1_2.setTextColor(getResources().getColor(R.color.main_text_color));
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Aynı anda 3'den fazla etkinlik çalıştıramazsın ne yazik ki :(", Toast.LENGTH_LONG).show();
                }




                break;

            case R.id.main_cardvw_2_1 :

                if ( etklinliksayisi <3 || durum_2_1 == 1)
                {
                    if ( durum_2_1 == 0)
                    {

                        main_chr_2_1.setBase(elapsedRealtime());
                        mdb.addData(3,1);
                        main_chr_2_1.start();
                        main_chr_2_1.setVisibility(View.VISIBLE);
                        main_cardvw_2_1.setCardBackgroundColor(getResources().getColor(R.color.main_cardvw_2_1));
                        main_btn_2_1.setImageResource(R.drawable.main_ent_sleepingicon_on);
                        main_txt_2_1.setTextColor(getResources().getColor(R.color.white));
                        durum_2_1 = 1;
                        etklinliksayisi++;
                    }

                    else
                    {
                        main_chr_2_1.setBase(elapsedRealtime());
                        mdb.updateFinishDate("Uyku");
                        main_chr_2_1.stop();
                        main_chr_2_1.setVisibility(View.INVISIBLE);
                        etklinliksayisi--;
                        durum_2_1 = 0;
                        main_cardvw_2_1.setCardBackgroundColor(getResources().getColor(R.color.white));
                        main_btn_2_1.setImageResource(R.drawable.main_ent_sleepingicon);
                        main_txt_2_1.setTextColor(getResources().getColor(R.color.main_text_color));

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Aynı anda 3'den fazla etkinlik çalıştıramazsın ne yazik ki :(", Toast.LENGTH_LONG).show();
                }




                break;

            case R.id.main_cardvw_2_2 :

                if ( etklinliksayisi <3 || durum_2_2 == 1)
                {
                    if ( durum_2_2 == 0)
                    {

                        main_chr_2_2.setBase(elapsedRealtime());
                        mdb.addData(4,1);
                        main_chr_2_2.start();
                        main_chr_2_2.setVisibility(View.VISIBLE);
                        main_cardvw_2_2.setCardBackgroundColor(getResources().getColor(R.color.main_cardvw_2_2));
                        main_btn_2_2.setImageResource(R.drawable.main_ent_socialicon_on);
                        main_txt_2_2.setTextColor(getResources().getColor(R.color.white));
                        durum_2_2=1;
                        etklinliksayisi++;
                    }

                    else
                    {
                        main_chr_2_2.setBase(elapsedRealtime());
                        mdb.updateFinishDate("Sosyallik");
                        main_chr_2_2.stop();
                        main_chr_2_2.setVisibility(View.INVISIBLE);
                        etklinliksayisi--;
                        durum_2_2=0;
                        main_cardvw_2_2.setCardBackgroundColor(getResources().getColor(R.color.white));
                        main_btn_2_2.setImageResource(R.drawable.main_ent_socialicon);
                        main_txt_2_2.setTextColor(getResources().getColor(R.color.main_text_color));

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Aynı anda 3'den fazla etkinlik çalıştıramazsın ne yazik ki :(", Toast.LENGTH_LONG).show();
                }




                break;

            case R.id.main_cardvw_3_1 :

                if ( etklinliksayisi <3 || durum_3_1 == 1)
                {
                    if (durum_3_1 == 0)
                    {

                        main_chr_3_1.setBase(elapsedRealtime());
                        mdb.addData(5,1);
                        main_chr_3_1.start();
                        main_chr_3_1.setVisibility(View.VISIBLE);
                        main_cardvw_3_1.setCardBackgroundColor(getResources().getColor(R.color.main_cardvw_3_1));
                        main_btn_3_1.setImageResource(R.drawable.main_ent_sporticon_on);
                        main_txt_3_1.setTextColor(getResources().getColor(R.color.white));
                        durum_3_1=1;
                        etklinliksayisi++;

                    }

                    else
                    {
                        main_chr_3_1.setBase(elapsedRealtime());
                        mdb.updateFinishDate("Spor");
                        main_chr_3_1.stop();
                        main_chr_3_1.setVisibility(View.INVISIBLE);
                        etklinliksayisi--;
                        durum_3_1=0;
                        main_cardvw_3_1.setCardBackgroundColor(getResources().getColor(R.color.white));
                        main_btn_3_1.setImageResource(R.drawable.main_ent_sporticon);
                        main_txt_3_1.setTextColor(getResources().getColor(R.color.main_text_color));

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Aynı anda 3'den fazla etkinlik çalıştıramazsın ne yazik ki :(", Toast.LENGTH_LONG).show();
                }




                break;

            case R.id.main_cardvw_3_2 :

                if ( etklinliksayisi <3 || durum_3_2==1)
                {
                    if (durum_3_2==0)
                    {

                        main_chr_3_2.setBase(elapsedRealtime());
                        mdb.addData(6,1);
                        main_chr_3_2.start();
                        main_chr_3_2.setVisibility(View.VISIBLE);
                        main_cardvw_3_2.setCardBackgroundColor(getResources().getColor(R.color.main_cardvw_3_2));
                        main_btn_3_2.setImageResource(R.drawable.main_ent_travelling_on);
                        main_txt_3_2.setTextColor(getResources().getColor(R.color.white));
                        durum_3_2=1;
                        etklinliksayisi++;
                    }

                    else
                    {
                        main_chr_3_2.setBase(elapsedRealtime());
                        mdb.updateFinishDate("Seyahat");
                        main_chr_3_2.stop();
                        main_chr_3_2.setVisibility(View.INVISIBLE);
                        etklinliksayisi--;
                        durum_3_2=0;
                        main_cardvw_3_2.setCardBackgroundColor(getResources().getColor(R.color.white));
                        main_btn_3_2.setImageResource(R.drawable.main_ent_travelling);
                        main_txt_3_2.setTextColor(getResources().getColor(R.color.main_text_color));

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Aynı anda 3'den fazla etkinlik çalıştıramazsın ne yazik ki :(", Toast.LENGTH_LONG).show();
                }




                break;


            //bottom bar case
            case R.id.footer_imgbtn_dashboard :

                Intent dashboard=new Intent();
                dashboard.setClass(MainActivity.this,Dashboard.class);
                startActivity(dashboard);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                break;

            case R.id.footer_imgbtn_profile :

                Intent profile=new Intent();
                profile.setClass(MainActivity.this,Profile.class);
                startActivity(profile);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                break;

        }

    }


}
