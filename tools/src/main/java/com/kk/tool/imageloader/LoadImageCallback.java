package com.kk.tool.imageloader;

import android.graphics.Bitmap;

/**
 * <font size="3" color="green"><b>.</b></font><p>
 * <p/>
 * <font size="2" color="green"><b>返回：.</b></font><p>
 * <p/>
 * <font size="1">Created on 2016-07-27.</font><p>
 * <p/>
 * <font size="1">@author LuoShuiquan.</font>
 */
public interface LoadImageCallback {
    void onBitmapLoaded(Bitmap bitmap);
}