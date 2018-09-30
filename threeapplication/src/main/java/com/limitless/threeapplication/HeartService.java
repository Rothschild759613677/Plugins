package com.limitless.threeapplication;

import android.util.Log;

import com.limitless.threeapplication.base.BaseService;

import java.util.Locale;

/**
 * Created by Nick on 2018/9/29
 *
 * @author Nick
 */
public class HeartService extends BaseService {

    @Override
    public void onCreate() {
        super.onCreate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    Log.d("HeartService", String.format(Locale.CHINA,"current num---%d", i));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
