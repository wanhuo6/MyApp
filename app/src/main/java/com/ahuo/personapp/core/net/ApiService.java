package com.ahuo.personapp.core.net;

import com.ahuo.personapp.base.BaseResponse;
import com.ahuo.personapp.core.config.FireUrl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public interface ApiService {

    @GET(FireUrl.LOGIN)
    Call<BaseResponse> checkAppUpgrade(@Query("build") String build);

}
