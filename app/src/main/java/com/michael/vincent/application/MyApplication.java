
package com.michael.vincent.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


}
