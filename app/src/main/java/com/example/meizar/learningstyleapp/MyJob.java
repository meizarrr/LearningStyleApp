package com.example.meizar.learningstyleapp;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by meizar on 03/06/18.
 */

public class MyJob extends Job {

    public static final String TAG = "my_job_tag";
    public Map<String, MyUsageStats> myUsageStatsList;

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, -1);
        long beginTime = calendar.getTimeInMillis();
        long endTime = System.currentTimeMillis();
        myUsageStatsList = new HashMap<String, MyUsageStats>();

        Context context = getContext();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);

        List<UsageStats> usageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_YEARLY, beginTime, endTime);
        if(usageStats.isEmpty()) Log.d("US", "Empty usage stats");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date threshold = new Date(0);
        String usageData = simpleDateFormat.format(threshold) + "\n";
        for(UsageStats singleUsageStat : usageStats){
            String packageName = singleUsageStat.getPackageName();
            long duration = singleUsageStat.getTotalTimeInForeground();
            MyUsageStats myUsageStats = new MyUsageStats(packageName, duration);
            myUsageStatsList.put(packageName, myUsageStats);
            //Date date = new Date(singleUsageStat.getTotalTimeInForeground());
            //String line = singleUsageStat.getPackageName() + "\n" + simpleDateFormat.format(date)+ "\n";
            //Log.d("Line", line);
            //usageData += line;
        }

        UsageEvents usageEvents = usageStatsManager.queryEvents(beginTime, endTime);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        while(usageEvents.hasNextEvent()){
            UsageEvents.Event event = new UsageEvents.Event();
            usageEvents.getNextEvent(event);
            if(event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND){
                MyUsageStats myUsageStats = myUsageStatsList.get(event.getPackageName());
                myUsageStats.frequency += 1;
                //String dateString = simpleDateFormat2.format(event.getTimeStamp());
                //Log.d("SWITCH" ,   dateString + " " + event.getPackageName());
            }
        }

        for(MyUsageStats result : myUsageStatsList.values()){
            Log.d(result.packageName, "Frequency : " + result.frequency + "Duration : " + result.duration);
        }
        return Result.SUCCESS;
    }

    public void runJobImmediately() {
        int jobId = new JobRequest.Builder(MyJob.TAG)
                .startNow()
                .build()
                .schedule();
    }
}
