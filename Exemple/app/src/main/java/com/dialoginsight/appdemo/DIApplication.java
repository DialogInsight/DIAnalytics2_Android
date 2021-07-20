package com.dialoginsight.appdemo;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.dialoginsight.dianalytics2.DIAnalytics;

public class DIApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        //

        //Set the base url for all calls to the server
        //DIAnalytics.setBaseUrl("http://docker.didev.ca/");
        //DIAnalytics.setBaseUrl("http://openfield.dev-an.hq2.rep/");
        DIAnalytics.setBaseUrl("http://jfc.ofsys.com/");

        //Enable logs to be displayed
        DIAnalytics.setLogEnabled(true);

        //Register your application to the service
        DIAnalytics.startWithApplicationId(this, "17340:RXsHqT6KcAVFlh96GFgLrb9q9x51p7Hq");

        //Application class super
        super.onCreate();
    }

}
