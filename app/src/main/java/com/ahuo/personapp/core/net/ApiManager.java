package com.ahuo.personapp.core.net;

import android.content.Context;

import com.ahuo.personapp.core.config.AppConfig;
import com.ahuo.tools.network.retrofit.KKNetWorkRequest;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public class ApiManager {
    private ApiService mApiService;

    private ApiManager() {
    }

    private static ApiManager sApiManager;

    public static ApiManager getInstance() {
        if (sApiManager == null) {
            sApiManager = new ApiManager();
        }
        return sApiManager;
    }


    public void init(Context context) {
        KKNetWorkRequest.getInstance().init(context, AppConfig.API_HOST);
        mApiService = KKNetWorkRequest.getInstance().create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
