package com.ahuo.personapp.Biz

import com.ahuo.personapp.base.BaseBiz
import com.ahuo.personapp.base.BaseResponse
import com.ahuo.personapp.core.net.ApiManager
import com.ahuo.personapp.entity.request.RegisterRequest

import retrofit2.Call

/**
 * Created on 2017-6-16

 * @author LiuHuiJie
 */
class RegisterBiz : BaseBiz<BaseResponse<*>, RegisterRequest>() {
    override fun buildNetWorkCall(requestEntity: RegisterRequest): Call<BaseResponse<*>> {
        return ApiManager.getInstance().apiService.register(requestEntity.nicName,requestEntity.account, requestEntity.password)
    }
}
