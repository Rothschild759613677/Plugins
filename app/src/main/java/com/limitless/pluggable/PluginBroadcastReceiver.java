package com.limitless.pluggable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Nick on 2018/9/30
 *
 * @author Nick
 *
 * 接收来自插件内的广播
 */
public class PluginBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到了来自插件的广播", Toast.LENGTH_SHORT).show();
    }
}
