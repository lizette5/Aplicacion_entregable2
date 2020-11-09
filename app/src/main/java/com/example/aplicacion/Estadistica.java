package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class Estadistica extends AppCompatActivity {
    LineChart mpLineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);

        mpLineChart=(LineChart) findViewById(R.id.line_chart);
        LineDataSet lineDataSet1= new LineDataSet(dataValues1(),"Tiempos");
        ArrayList<ILineDataSet>dataSets=new ArrayList<>();
        dataSets.add(lineDataSet1);
        LineData data=new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
    }
    private ArrayList<Entry> dataValues1(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0,20));
        dataVals.add(new Entry(1,24));
        dataVals.add(new Entry(2,2));
        dataVals.add(new Entry(3,10));
        dataVals.add(new Entry(4,28));
        dataVals.add(new Entry(5,14));
        dataVals.add(new Entry(6,16));


        return dataVals;
    }
}