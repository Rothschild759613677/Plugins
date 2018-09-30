package com.limitless.threeapplication;

/**
 * Created by Nick on 2018/9/29
 *
 * @author Nick
 */
public interface Constant {

    String TAG = "Nick";

    /**
     * 接收来自插件内的动态广播
     */
    String RECEIVER_DYNAMIC_LOCAL = "com.limitless.threeapplication.receiver.DynamicBroadcastReceiver";

    /**
     * 接收来自插件内的静态广播
     */
    String RECEIVER_STATIC_LOCAL = "com.limitless.threeapplication.receiver.StaticBroadcastReceiver";

    /**
     * 发送广播到宿主
     */
    String SEND_RECEIVER_HOST = "com.limitless.pluggable.PluginBroadcastReceiver";
}
