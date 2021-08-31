package com.dialoginsight.appdemo;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.dialoginsight.dianalytics2.DIAnalytics;

public class DIApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        //

        //Set the base url for all calls to the server
        DIAnalytics.setBaseUrl("http://app.dialoginsight.com/");

        //Enable logs to be displayed
        DIAnalytics.setLogEnabled(true);

        //Register your application to the service
        DIAnalytics.startWithApplicationId(this, "[DiApplicationId]");

        //Application class super
        super.onCreate();
    }

}
