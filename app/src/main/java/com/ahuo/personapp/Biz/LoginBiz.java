package com.ahuo.personapp.Biz;

import com.ahuo.personapp.base.BaseBiz;
import com.ahuo.personapp.core.net.ApiManager;
import com.kk.http.retrofit.BaseRequestEntity;
import com.kk.http.retrofit.BaseResponseEntity;

import retrofit2.Call;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public class LoginBiz extends BaseBiz<BaseResponseEntity,BaseRequestEntity>{


    @Override
    protected Call<BaseResponseEntity> buildNetWorkCall(BaseRequestEntity requestEntity) {
        return ApiManager.getInstance().getApiService().getLogin();
    }
}
