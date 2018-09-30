package com.limitless.threeapplication.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.limitless.threeapplication.base.BaseBroadcastReceiver;

/**
 * Created by Nick on 2018/9/29
 *
 * @author Nick
 *
 * 接收插件内部的动态广播
 */
public class DynamicBroadcastReceiver extends BaseBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        Toast.makeText(context,"接收插件内部的动态广播",Toast.LENGTH_LONG).show();
    }
}
