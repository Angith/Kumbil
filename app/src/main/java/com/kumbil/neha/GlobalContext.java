package com.kumbil.neha;

import android.app.Application;
import android.content.Context;

public class GlobalContext extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
