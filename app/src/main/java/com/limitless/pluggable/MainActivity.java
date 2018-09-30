package com.limitless.pluggable;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.limitless.pluggable.proxy.PluginManager;
import com.limitless.pluggable.proxy.ProxyActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private PluginBroadcastReceiver pluginBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPlugin();
            }
        });

        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipEvent();
            }
        });

        findViewById(R.id.btn_send_broadcast_to_plugin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcastToPlugin();
            }
        });

        pluginBroadcastReceiver = new PluginBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.limitless.pluggable.PluginBroadcastReceiver");
        registerReceiver(pluginBroadcastReceiver,intentFilter);
    }

    /**
     * 向插件中发送广播，插件中的广播接收者是静态注册的
     */
    private void sendBroadcastToPlugin() {
        Intent pluginIntent = new Intent();
        pluginIntent.setAction("com.limitless.threeapplication.receiver.HostBroadcastReceiver");
        sendBroadcast(pluginIntent);
    }

    private void skipEvent() {
        Intent intent = new Intent(this,ProxyActivity.class);
        intent.putExtra("className", PluginManager.getInstance().getPackageInfo().activities[0].name);
        startActivity(intent);
    }


    private void loadPlugin() {

        String apkName = "threeapplication-debug.apk";

        File filesDir = getDir("plugin", Context.MODE_PRIVATE);
        String filePath = new File(filesDir, apkName).getAbsolutePath();

        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                Log.d(TAG, "old apk file delete success");
            }
        }

        //从SD卡上复制新的插件到应用中
        try {
            FileInputStream is = new FileInputStream(new File(Environment.getExternalStorageDirectory(), apkName).getAbsolutePath());
            FileOutputStream os = new FileOutputStream(filePath);

            int len ;

            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }

            //验证是否复制完成
            File newFile = new File(filePath);
            if (newFile.exists()) {
                Toast.makeText(this, "copy apk plugin success", Toast.LENGTH_SHORT).show();
            }

            //复制完成后，开始加载插件到内存
            PluginManager.getInstance().loadPlugin(this, apkName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(pluginBroadcastReceiver);

    }
}
