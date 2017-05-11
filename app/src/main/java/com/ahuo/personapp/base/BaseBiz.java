package com.ahuo.personapp.base;

import com.ahuo.personapp.MyApplication;
import com.ahuo.personapp.R;
import com.kk.http.retrofit.BaseRequestEntity;
import com.kk.http.retrofit.BaseResponseEntity;
import com.kk.http.retrofit.KKNetWorkRequest;
import com.kk.tool.util.NetWorkUtils;

import retrofit2.Call;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public abstract class BaseBiz<T extends BaseResponseEntity, M extends BaseRequestEntity> {

    protected abstract Call<T> buildNetWorkCall(M requestEntity);

    public void execute(M requestEntity) {
        if (!NetWorkUtils.isNetConnect(MyApplication.getApp())) {
            requestEntity.getkKNetworkResponse().onDataError(requestEntity.getRequestCode(),0, MyApplication.getApp().getString(R.string.net_error));
            return;
        }
        KKNetWorkRequest.getInstance().asyncNetWork(requestEntity.getTag(), requestEntity.getRequestCode(), buildNetWorkCall(requestEntity), requestEntity.getkKNetworkResponse());
    }

    public void unSubscribe(String tag) {
        KKNetWorkRequest.getInstance()
                .cancelTagCall(tag);
    }
}
