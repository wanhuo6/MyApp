package com.ahuo.personapp.core.net;

import com.ahuo.personapp.entity.response.GetUserResponse;
import com.ahuo.personapp.entity.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public interface ApiService {

    @GET(NetUrl.LOGIN)
    Call<LoginResponse> getLogin();

    @GET(NetUrl.GET_USERS)
    Call<GetUserResponse> getUsers();

}
