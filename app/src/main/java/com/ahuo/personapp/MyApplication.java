package com.ahuo.personapp;

import android.app.Application;

import com.ahuo.personapp.core.net.ApiManager;
import com.kk.tool.util.MLog;
import com.kk.tool.util.ToastUtils;

/**
 * Created on 17-5-11
 *
 * @author liuhuijie
 */

public class MyApplication extends Application {

    private static MyApplication sKKApplication;

    public static MyApplication getApp() {
        return sKKApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sKKApplication = this;
        init();
    }

    private void init() {
        ApiManager.getInstance().init(this);
        ToastUtils.init(this);
        MLog.init(true,this.getString(R.string.app_name));

    }


}
