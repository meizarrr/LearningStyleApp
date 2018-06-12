package com.example.meizar.learningstyleapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by meizar on 03/06/18.
 */

public class MyJobCreator implements JobCreator {
    @Override
    @Nullable
    public Job create(@NonNull String tag){
        switch (tag){
            case MyJob.TAG : return new MyJob();
            default : return null;
        }
    }
}