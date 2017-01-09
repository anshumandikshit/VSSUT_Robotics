package com.robotix_vssut.welcomeanimationapp;

/**
 * Created by Hp on 27-12-2016.
 */

import android.app.Application;
import com.pushbots.push.Pushbots;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Pushbots Library
        Pushbots.sharedInstance().init(this);
    }
}