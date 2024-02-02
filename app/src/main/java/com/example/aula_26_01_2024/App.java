package com.example.aula_26_01_2024;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class App extends Application {
    static public Context ctx;
    public static final String TAG = "Xpto";

    @Override
    public void onCreate() {

        super.onCreate();
        ctx = getApplicationContext();
        Log.i(TAG, "App");
    }

}