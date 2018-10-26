package com.app.cronia.cronia10;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private ImageButton footer_imgbtn_dashboard;
    private ImageButton footer_imgbtn_home;
    private ImageButton footer_imgbtn_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        footer_imgbtn_dashboard = (ImageButton) findViewById(R.id.footer_imgbtn_dashboard);
        footer_imgbtn_home = (ImageButton) findViewById(R.id.footer_imgbtn_home);
        footer_imgbtn_profile = (ImageButton) findViewById(R.id.footer_imgbtn_profile);

        // toolbar icons on - off state
        footer_imgbtn_dashboard.setImageResource(R.drawable.main_dashboardbutton_off);
        footer_imgbtn_home.setImageResource(R.drawable.main_homebutton_off);
        footer_imgbtn_profile.setImageResource(R.drawable.main_profilebutton_on);


        footer_imgbtn_dashboard.setOnClickListener(this);
        footer_imgbtn_home.setOnClickListener(this);
        footer_imgbtn_profile.setOnClickListener(this);



    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(Profile.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
        //Slide Animation
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.footer_imgbtn_dashboard:

                Intent home = new Intent();
                home.setClass(Profile.this, MainActivity.class);
                startActivity(home);

                break;

            case R.id.footer_imgbtn_profile:

                Intent profile = new Intent();
                profile.setClass(Profile.this, Profile.class);
                startActivity(profile);

                break;
        }

    }
}