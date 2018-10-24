package com.app.cronia.cronia10;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.app.cronia.cronia10.Adapter.Dashboard_adapter_listview;
import com.app.cronia.cronia10.Database.DatabaseHelper;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

import static com.app.cronia.cronia10.Database.DatabaseHelper.A_NAME;
import static com.app.cronia.cronia10.Database.DatabaseHelper.UA_FINISH_DATE;
import static com.app.cronia.cronia10.Database.DatabaseHelper.UA_START_DATE;


public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private ImageButton footer_imgbtn_dashboard;
    private ImageButton footer_imgbtn_home;
    private ImageButton footer_imgbtn_profile;
    PieChart pieChart;

    //dashboard listview
    private ArrayList<HashMap<String, String>> list;
    public static final String FIRST_COLUMN = "First";
    public static final String SECOND_COLUMN = "Second";
    public static final String THIRD_COLUMN = "Third";
    public static final String FOURTH_COLUMN = "Fourth";
    public static final String FIFTH_COLUMN = "Fifth";
    private ListView listView;
    private Dashboard_adapter_listview adapter;
    TextView dashboard_txt_MaxActivity,dashboard_txt_MaxTime;

    final DatabaseHelper mdb = new DatabaseHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        dashboard_txt_MaxActivity = (TextView) findViewById(R.id.dashboard_txt_MaxActivity);
        dashboard_txt_MaxTime = (TextView) findViewById(R.id.dashboard_txt_maxTime);


        mdb.maxActionDetail();
        dashboard_txt_MaxActivity.setText(mdb.getMaxAction());
        dashboard_txt_MaxTime.setText( String.valueOf(mdb.getMaxTime()));
        footer_imgbtn_dashboard = (ImageButton) findViewById(R.id.footer_imgbtn_dashboard);
        footer_imgbtn_home = (ImageButton) findViewById(R.id.footer_imgbtn_home);
        footer_imgbtn_profile = (ImageButton) findViewById(R.id.footer_imgbtn_profile);

        // toolbar icons on - off state
        footer_imgbtn_dashboard.setImageResource(R.drawable.main_dashboardbutton_on);
        footer_imgbtn_home.setImageResource(R.drawable.main_homebutton_off);
        footer_imgbtn_profile.setImageResource(R.drawable.main_profilebutton_off);

        //click listener toolbar

        footer_imgbtn_dashboard.setOnClickListener(this);
        footer_imgbtn_home.setOnClickListener(this);
        footer_imgbtn_profile.setOnClickListener(this);


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

        Cursor cursor = mdb.grafikListe();

        while (cursor.moveToNext()){

            int value = Integer.parseInt(cursor.getString(1));

            yValues.add(new PieEntry(value, cursor.getString(0)));

        }





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
        list = new ArrayList<HashMap<String, String>>();
        listView = (ListView) findViewById(R.id.dashboard_listview);
        adapter = new Dashboard_adapter_listview(this, list);

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

            Cursor cursor = mdb.Listele();

            while (cursor.moveToNext()){
                HashMap<String,String> liste = new HashMap<>();
                liste.put(FIRST_COLUMN,cursor.getString(0));
                liste.put(SECOND_COLUMN,cursor.getString(1));
                liste.put(THIRD_COLUMN,cursor.getString(2));
                liste.put(FOURTH_COLUMN,cursor.getString(3));
                list.add(liste);
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

        switch (view.getId()) {
            case R.id.footer_imgbtn_home:

                Intent home = new Intent();
                home.setClass(Dashboard.this, MainActivity.class);
                startActivity(home);

                break;

            case R.id.footer_imgbtn_profile:

                Intent profile = new Intent();
                profile.setClass(Dashboard.this, Profile.class);
                startActivity(profile);

                break;
        }
    }








}
