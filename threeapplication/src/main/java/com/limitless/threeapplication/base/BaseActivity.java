package com.limitless.threeapplication.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.limitless.plugstandard.IActivity;

import java.util.Objects;

/**
 * Created by Nick on 2018/9/28
 *
 * @author Nick
 */
public class BaseActivity extends Activity implements IActivity {

    protected  Activity activity;

    @Override
    public void attach(Activity proxyActivity) {
        this.activity=proxyActivity;
    }


    @Override
    public void onCreate(Bundle bundle) {
        if (activity == null) {
            super.onCreate(bundle);
        }
    }

    @Override
    public void onStart() {
        if (activity == null) {
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (activity == null) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (activity == null) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (activity == null) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (activity == null) {
            super.onDestroy();
        }
    }

    @Override
    public void onRestart() {
        if (activity == null) {
            super.onRestart();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

    @Override
    public boolean onTouch(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public Window getWindow() {
        if (activity != null) {
            return activity.getWindow();
        }
        return super.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        if (activity != null) {
            activity.getWindowManager();
        }
        return super.getWindowManager();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if (activity != null) {
            return activity.getApplicationInfo();
        }
        return super.getApplicationInfo();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (activity != null) {
            return activity.getLayoutInflater();
        }
        return super.getLayoutInflater();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (activity != null) {
            return activity.getClassLoader();
        }
        return super.getClassLoader();
    }

    @Override
    public Intent getIntent() {
        if (activity != null) {
            return activity.getIntent();
        }
        return super.getIntent();
    }

    @Override
    public <T extends View> T  findViewById(int id) {
        if (activity != null) {
            return activity.findViewById(id);
        }
        return super.findViewById(id);
    }

    @Override
    public void setContentView(View view) {
        if (activity != null) {
            activity.setContentView(view);
        }
        super.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (activity != null) {
            activity.setContentView(layoutResID);
        }
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (activity != null) {
            activity.setContentView(view,params);
        }
        super.setContentView(view, params);
    }

    @Override
    public void startActivity(Intent intent) {
        if (activity != null) {
            Intent skipIntent = new Intent();
            skipIntent.putExtra("className", Objects.requireNonNull(intent.getComponent()).getClassName());
            activity.startActivity(skipIntent);
        }else {
            super.startActivity(intent);
        }
    }

    @Override
    public ComponentName startService(Intent service) {
        if (activity != null) {
            Intent serviceIntent = new Intent();
            serviceIntent.putExtra("serviceClassName", Objects.requireNonNull(service.getComponent()).getClassName());
            return activity.startService(serviceIntent);
        }else {
            return super.startService(service);
        }
    }


    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {

        if (activity != null) {
            return activity.registerReceiver(receiver, filter);
        }else {
            return super.registerReceiver(receiver, filter);
        }
    }

    @Override
    public void sendBroadcast(Intent intent) {
        if (activity != null) {
            activity.sendBroadcast(intent);
        }else {
            super.sendBroadcast(intent);
        }
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        if (activity != null) {
            activity.unregisterReceiver(receiver);
        }else {
            super.unregisterReceiver(receiver);
        }
    }
}
