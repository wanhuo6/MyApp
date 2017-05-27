package com.ahuo.personapp.Biz;

import com.ahuo.personapp.base.BaseBiz;
import com.ahuo.personapp.core.net.ApiManager;
import com.ahuo.personapp.entity.response.GetUserResponse;
import com.ahuo.tools.network.retrofit.BaseRequestEntity;

import retrofit2.Call;

/**
 * Created on 17-5-27
 *
 * @author liuhuijie
 */

public class GetUsersBiz extends BaseBiz<GetUserResponse,BaseRequestEntity> {

    @Override
    protected Call<GetUserResponse> buildNetWorkCall(BaseRequestEntity requestEntity) {
        return ApiManager.getInstance().getApiService().getUsers();
    }
}
