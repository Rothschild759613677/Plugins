package com.limitless.threeapplication.receiver;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.limitless.threeapplication.base.BaseBroadcastReceiver;

/**
 * Created by Nick on 2018/9/30
 *
 * @author Nick
 * <p>
 * 接收来自宿主的广播
 */
public class HostBroadcastReceiver extends BaseBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        Toast.makeText(context, "接收来自宿主的广播", Toast.LENGTH_SHORT).show();
    }
}
