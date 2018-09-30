package com.limitless.pluggable.proxy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.limitless.plugstandard.IService;

import java.lang.reflect.Constructor;

/**
 * Created by Nick on 2018/9/29
 *
 * @author Nick
 * <p>
 * 代理的服务
 */
public class ProxyService extends Service {

    private IService service;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init(intent);
        return service.onStartCommand(intent, flags, startId);
    }

    private void init(Intent intent) {

        String serviceClassName = intent.getStringExtra("serviceClassName");

        try {
            Class<?> aClass = PluginManager.getInstance().getDexClassLoader().loadClass(serviceClassName);
            Constructor<?> constructor = aClass.getConstructor();
            Object object = constructor.newInstance();
            service = (IService) object;
            service.attach(this);
            service.onCreate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (service != null) {
            service.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (service != null) {
            service.onLowMemory();
        }
    }


    @Override
    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        return service.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        if (service != null) {
            service.onRebind(intent);
        }
    }
}
