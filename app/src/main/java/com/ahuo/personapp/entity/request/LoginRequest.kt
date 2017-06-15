package com.ahuo.personapp.entity.request

import com.ahuo.tools.network.retrofit.BaseRequestEntity
import com.ahuo.tools.network.retrofit.KKNetworkResponse

/**
 * Created on 2017-6-15
 *
 * @author LiuHuiJie
 */
class LoginRequest(tag: String?, requestCode: Int, kKNetworkResponse: KKNetworkResponse<*>?,var account:String,var password:String) : BaseRequestEntity(tag, requestCode, kKNetworkResponse)