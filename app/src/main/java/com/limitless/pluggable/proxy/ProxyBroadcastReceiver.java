package com.limitless.pluggable.proxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.limitless.plugstandard.IBroadcastReceiver;

import java.lang.reflect.Constructor;

/**
 * Created by Nick on 2018/9/29
 *
 * @author Nick
 */
public class ProxyBroadcastReceiver extends BroadcastReceiver {

    private IBroadcastReceiver broadcastReceiver;

    public ProxyBroadcastReceiver(String className) {

        try {
            Class<?> aClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);
            Constructor<?> constructor = aClass.getConstructor();
            Object object = constructor.newInstance();
            broadcastReceiver = (IBroadcastReceiver) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        broadcastReceiver.onReceive(context, intent);
    }
}
