package com.ahuo.personapp.Biz;

import com.ahuo.personapp.base.BaseBiz;
import com.ahuo.personapp.core.net.ApiManager;
import com.ahuo.personapp.entity.response.LoginResponse;
import com.kk.http.retrofit.BaseRequestEntity;

import retrofit2.Call;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public class LoginBiz extends BaseBiz<LoginResponse,BaseRequestEntity>{


    @Override
    protected Call<LoginResponse> buildNetWorkCall(BaseRequestEntity requestEntity) {
        return ApiManager.getInstance().getApiService().getLogin();
    }
}
