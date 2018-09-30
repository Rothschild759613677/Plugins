package com.limitless.threeapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.limitless.threeapplication.R;
import com.limitless.threeapplication.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_activity_main);

        findViewById(R.id.btn_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (activity == null) {
                    intent = new Intent(getBaseContext(), SecondActivity.class);
                }else {
                    intent = new Intent(activity,SecondActivity.class);
                }
                startActivity(intent);
            }
        });

    }
}
