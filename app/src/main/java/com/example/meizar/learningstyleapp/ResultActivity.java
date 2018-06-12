package com.example.meizar.learningstyleapp;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.evernote.android.job.JobRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        int vTotal = getIntent().getIntExtra("vTotal", 0);
        int aTotal = getIntent().getIntExtra("aTotal", 0);
        int rTotal = getIntent().getIntExtra("rTotal", 0);
        int kTotal = getIntent().getIntExtra("kTotal", 0);

        TextView vText = (TextView) findViewById(R.id.vText);
        TextView aText = (TextView) findViewById(R.id.aText);
        TextView rText = (TextView) findViewById(R.id.rText);
        TextView kText = (TextView) findViewById(R.id.kText);
        TextView usageStatsText = (TextView) findViewById(R.id.usageStatsText);

        vText.setText("Visual : " + String.valueOf(vTotal));
        aText.setText("Aural : " + String.valueOf(aTotal));
        rText.setText("Read/Write : " + String.valueOf(rTotal));
        kText.setText("Kinesthetic : " + String.valueOf(kTotal));

        MyJob myJob = new MyJob();
        myJob.runJobImmediately();
    }
}
