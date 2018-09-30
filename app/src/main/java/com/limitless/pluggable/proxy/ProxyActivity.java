package com.limitless.pluggable.proxy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.limitless.plugstandard.IActivity;

import java.lang.reflect.Constructor;

/**
 * Created by Nick on 2018/9/28
 *
 * @author Nick
 * <p>
 * 代理的Activity
 */
public class ProxyActivity extends Activity {

    private IActivity iActivity;
    private ProxyBroadcastReceiver proxyBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String className = getIntent().getStringExtra("className");

        try {
            Class<?> activityClass = getClassLoader().loadClass(className);

            Constructor<?> constructor = activityClass.getConstructor();

            Object object = constructor.newInstance();

            iActivity = (IActivity) object;

            iActivity.attach(this);
            Bundle bundle = new Bundle();
            iActivity.onCreate(bundle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        iActivity.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        iActivity.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iActivity.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        iActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        iActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iActivity.onDestroy();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    public void startActivity(Intent intent) {
        String pluginClassName = intent.getStringExtra("className");
        Intent proxyIntent = new Intent(this, ProxyActivity.class);
        proxyIntent.putExtra("className", pluginClassName);
        super.startActivity(proxyIntent);
    }

    @Override
    public ComponentName startService(Intent service) {

        String pluginServiceClassName = service.getStringExtra("serviceClassName");
        Intent proxyServiceIntent = new Intent(this, ProxyService.class);
        proxyServiceIntent.putExtra("serviceClassName", pluginServiceClassName);
        return super.startService(proxyServiceIntent);
    }


    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        proxyBroadcastReceiver = new ProxyBroadcastReceiver(receiver.getClass().getName());
        return super.registerReceiver(proxyBroadcastReceiver, filter);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        super.unregisterReceiver(proxyBroadcastReceiver);
    }
}
