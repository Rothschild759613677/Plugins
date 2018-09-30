package com.limitless.threeapplication.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.limitless.threeapplication.Constant;
import com.limitless.threeapplication.HeartService;
import com.limitless.threeapplication.R;
import com.limitless.threeapplication.base.BaseActivity;
import com.limitless.threeapplication.receiver.DynamicBroadcastReceiver;

/**
 * Created by Nick on 2018/9/29
 *
 * @author Nick
 */
public class SecondActivity extends BaseActivity implements View.OnClickListener {


    private Button btnStartService;
    private Button btnSendBroadcast;

    private Button btnSendBroadcastHost;

    private Button btnSendBroadcastStaticLocal;

    private DynamicBroadcastReceiver dynamicBroadcastReceiver;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.three_activity_second);

        btnStartService=findViewById(R.id.second_btn_start_service);

        btnSendBroadcastHost=findViewById(R.id.second_btn_send_broadcast_host);

        btnSendBroadcast=findViewById(R.id.second_btn_send_broadcast_dynamic_local);
        btnSendBroadcastStaticLocal=findViewById(R.id.second_btn_send_broadcast_static_local);

        btnStartService.setOnClickListener(this);
        btnSendBroadcast.setOnClickListener(this);
        btnSendBroadcastHost.setOnClickListener(this);
        btnSendBroadcastStaticLocal.setOnClickListener(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.RECEIVER_DYNAMIC_LOCAL);
        dynamicBroadcastReceiver = new DynamicBroadcastReceiver();
        registerReceiver(dynamicBroadcastReceiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.second_btn_start_service:
                Intent serviceIntent;
                if (activity == null) {
                    Toast.makeText(getApplicationContext(),"独立应用",Toast.LENGTH_LONG).show();
                    serviceIntent = new Intent(getBaseContext(), HeartService.class);
                }else {
                    //开启服务
                    serviceIntent = new Intent(activity, HeartService.class);
                    Toast.makeText(activity,"插件应用",Toast.LENGTH_LONG).show();
                }
                startService(serviceIntent);
                break;
            case R.id.second_btn_send_broadcast_host:
                //发送动态广播到宿主中
                Intent hostIntent = new Intent();
                hostIntent.setAction(Constant.SEND_RECEIVER_HOST);
                sendBroadcast(hostIntent);
                break;
            case R.id.second_btn_send_broadcast_dynamic_local:
                //发送动态广播到本地
                Intent intent = new Intent();
                intent.setAction(Constant.RECEIVER_DYNAMIC_LOCAL);
                sendBroadcast(intent);
                break;
            case R.id.second_btn_send_broadcast_static_local:
                //发送静态广播到插件内部
                Intent localIntent = new Intent();
                localIntent.setAction(Constant.RECEIVER_STATIC_LOCAL);
                sendBroadcast(localIntent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dynamicBroadcastReceiver);
    }
}
