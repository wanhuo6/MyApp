package com.ahuo.personapp.utils;


import android.view.View;

import java.util.Calendar;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public abstract class MyClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 800;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onKKClick(v);
        }
    }

    protected abstract void onKKClick(View v);
}

