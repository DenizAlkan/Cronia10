package com.app.cronia.cronia10.Graphic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.cronia.cronia10.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Graphic_Pie_Chart extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphic_pie_chart);

        pieChart = (PieChart) findViewById(R.id.pie_chart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f,"Yemek"));
        yValues.add(new PieEntry(34f,"Kitap Okuma"));
        yValues.add(new PieEntry(34f,"Uyku"));
        yValues.add(new PieEntry(34f,"Spor"));


        PieDataSet dataSet = new PieDataSet(yValues, "Aktiviteler");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);


    }
}
