package com.ahuo.personapp.Biz;

import com.ahuo.personapp.base.BaseBiz;
import com.ahuo.personapp.core.net.ApiManager;
import com.ahuo.personapp.entity.request.LoginRequest;
import com.ahuo.personapp.entity.response.LoginResponse;

import retrofit2.Call;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public class LoginBiz extends BaseBiz<LoginResponse,LoginRequest>{

    @Override
    protected Call<LoginResponse> buildNetWorkCall(LoginRequest requestEntity) {
        return ApiManager.getInstance().getApiService().getLogin(requestEntity.getAccount(),requestEntity.getPassword());
    }
}
