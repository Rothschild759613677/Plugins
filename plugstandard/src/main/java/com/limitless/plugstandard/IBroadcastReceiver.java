package com.limitless.plugstandard;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Nick on 2018/9/29
 *
 * @author Nick
 */
public interface IBroadcastReceiver {
    void onReceive(Context context, Intent intent);
}
