package com.ahuo.personapp.core.net;

import com.ahuo.personapp.entity.response.GetUserResponse;
import com.ahuo.personapp.entity.response.LoginResponse;
import com.ahuo.personapp.entity.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public interface ApiService {
    @FormUrlEncoded
    @POST(NetUrl.REGISTER)
    Call<RegisterResponse> register(@Field("name") String name, @Field("account") String account, @Field("password") String password);

    @GET(NetUrl.LOGIN)
    Call<LoginResponse> getLogin(@Query("account") String account, @Query("password") String password);

    @GET(NetUrl.GET_USERS)
    Call<GetUserResponse> getUsers();

}
