package com.muelpatmore.movielist;

import android.app.Application;
import android.content.Context;

public class MovieListApp extends Application {

    public static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}