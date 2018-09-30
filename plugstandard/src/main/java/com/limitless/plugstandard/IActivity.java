package com.limitless.plugstandard;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by Nick on 2018/9/28
 *
 * @author Nick
 *
 * Activity的标准
 */
public interface IActivity {


    void attach(Activity proxyActivity);

    void onCreate(Bundle bundle);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onRestart();

    void onSaveInstanceState(Bundle bundle);

    boolean onTouch(MotionEvent motionEvent);

    void onBackPressed();



}
