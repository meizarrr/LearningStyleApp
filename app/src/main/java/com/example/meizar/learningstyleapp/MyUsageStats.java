package com.example.meizar.learningstyleapp;

/**
 * Created by meizar on 12/06/18.
 */

public class MyUsageStats {
    String packageName;
    int frequency;
    long duration;

    public MyUsageStats(){}
    public MyUsageStats(String packageName, long duration){
        this.packageName = packageName;
        this.duration = duration;
    }

    public void setFrequency(int frequency){
        this.frequency = frequency;
    }
}
