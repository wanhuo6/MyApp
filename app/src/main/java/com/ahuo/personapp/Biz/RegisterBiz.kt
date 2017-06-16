package com.ahuo.personapp.Biz

import com.ahuo.personapp.base.BaseBiz
import com.ahuo.personapp.base.BaseResponse
import com.ahuo.personapp.core.net.ApiManager
import com.ahuo.personapp.entity.request.RegisterRequest
import com.ahuo.personapp.entity.response.RegisterResponse

import retrofit2.Call

/**
 * Created on 2017-6-16

 * @author LiuHuiJie
 */
class RegisterBiz : BaseBiz<RegisterResponse, RegisterRequest>() {
    override fun buildNetWorkCall(requestEntity: RegisterRequest): Call<RegisterResponse> {
        return ApiManager.getInstance().apiService.register(requestEntity.nicName,requestEntity.account, requestEntity.password)
    }
}
