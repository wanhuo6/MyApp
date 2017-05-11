package com.ahuo.personapp.core.net;

import com.kk.http.retrofit.BaseResponseEntity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public interface ApiService {

    @GET(NetUrl.LOGIN)
    Call<BaseResponseEntity> getLogin();

}
