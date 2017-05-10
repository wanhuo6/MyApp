package com.kk.tool.util;

import android.view.View;

import java.util.Calendar;

/**
 * Created on 2016-08-12.
 *
 * @author GuoNing.
 */
public abstract class KKOnClickListener implements View.OnClickListener {

    private static final int MIN_CLICK_DELAY_TIME = 500;

    private long lastClickTime = 0;

    protected abstract void onKKClick(View v);

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onKKClick(v);
        }
    }
}
