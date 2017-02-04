package com.npc.shop.app;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * Created by Lenovo on 1/22/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
