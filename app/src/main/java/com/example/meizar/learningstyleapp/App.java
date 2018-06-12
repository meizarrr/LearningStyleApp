package com.example.meizar.learningstyleapp;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;
import com.evernote.android.job.JobManager;

/**
 * Created by meizar on 03/06/18.
 */

public class App extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        JobManager.create(this).addJobCreator(new MyJobCreator());
    }
}
