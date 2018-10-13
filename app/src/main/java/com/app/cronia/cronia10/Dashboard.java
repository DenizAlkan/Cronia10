package com.app.cronia.cronia10;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;

import com.app.cronia.cronia10.Adapter.Dashboard_adapter_listview;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class Dashboard extends AppCompatActivity implements View.OnClickListener  {

    private ImageButton footer_imgbtn_dashboard;
    private ImageButton footer_imgbtn_home;
    private ImageButton footer_imgbtn_profile;
    PieChart pieChart;

    //dashboard listview
    private ArrayList<HashMap<String, String>> list;
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";
    public static final String THIRD_COLUMN="Third";
    public static final String FOURTH_COLUMN="Fourth";
    public static final String FIFTH_COLUMN="Fifth";
    private ListView listView;
    private Dashboard_adapter_listview adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        footer_imgbtn_dashboard = (ImageButton) findViewById(R.id.footer_imgbtn_dashboard);
        footer_imgbtn_home = (ImageButton) findViewById(R.id.footer_imgbtn_home);
        footer_imgbtn_profile = (ImageButton) findViewById(R.id.footer_imgbtn_profile);

        // toolbar icons on - off state
        footer_imgbtn_dashboard.setImageResource(R.drawable.main_dashboardbutton_on);
        footer_imgbtn_home.setImageResource(R.drawable.main_homebutton_off);
        footer_imgbtn_profile.setImageResource(R.drawable.main_profilebutton_off);

        //click listener toolbar

        footer_imgbtn_dashboard .setOnClickListener(this);
        footer_imgbtn_home .setOnClickListener(this);
        footer_imgbtn_profile .setOnClickListener(this);


        //pie chart
        pieChart = (PieChart) findViewById(R.id.pie_chart1);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(0, 0, 0, 0);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(60f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f,"Yemek"));
        yValues.add(new PieEntry(34f,"Kitap Okuma"));
        yValues.add(new PieEntry(34f,"Uyku"));
        yValues.add(new PieEntry(34f,"Spor"));


        PieDataSet dataSet = new PieDataSet(yValues, "Aktiviteler");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.LIBERTY_COLORS
        );


        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);


        //dashboard listview
        list=new ArrayList<HashMap<String,String>>();
        listView=(ListView)findViewById(R.id.dashboard_listview);
        adapter=new Dashboard_adapter_listview(this, list);

        new VeriGetir().execute();



    }

    private void populateList() {
        // TODO Auto-generated method stub



    }

    private class VeriGetir extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }



        @Override
        protected Void doInBackground(Void... voids) {


            for (int i=0; i<15; i++)
            {
                String firma = "ebe";
                String kalkis = "nin";
                String varis = "a";
                String saat = "m";


                HashMap<String,String> hashmap=new HashMap<String, String>();
                hashmap.put(FIRST_COLUMN, firma);
                hashmap.put(SECOND_COLUMN, kalkis);
                hashmap.put(THIRD_COLUMN, varis);
                hashmap.put(FOURTH_COLUMN, saat);

                list.add(hashmap);

            }








            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // lv.setAdapter(adapter);

            listView.setAdapter(adapter);



        }
    }






    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.footer_imgbtn_home :

                Intent home=new Intent();
                home.setClass(Dashboard.this,MainActivity.class);
                startActivity(home);

                break;

            case R.id.footer_imgbtn_profile :

                Intent profile=new Intent();
                profile.setClass(Dashboard.this,Profile.class);
                startActivity(profile);

                break;
        }
    }
}
