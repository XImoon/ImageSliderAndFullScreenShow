package com.example.ximoon.mytesting;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by ximoon on 15/12/22.
 */
public class TestingApplication extends Application{

    private static TestingApplication instance;

    public static TestingApplication getInstance(){
        if (instance == null){
            synchronized (TestingApplication.class){
                instance = new TestingApplication();
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
