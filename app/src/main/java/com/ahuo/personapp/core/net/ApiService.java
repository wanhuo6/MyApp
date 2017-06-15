package com.ahuo.personapp.core.net;

import com.ahuo.personapp.entity.response.GetUserResponse;
import com.ahuo.personapp.entity.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public interface ApiService {

    @GET(NetUrl.LOGIN)
    Call<LoginResponse> getLogin(@Query("account") String account, @Query("password") String password);

    @GET(NetUrl.GET_USERS)
    Call<GetUserResponse> getUsers();

}
