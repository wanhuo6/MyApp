package com.ahuo.personapp.entity.request

import com.ahuo.tools.network.retrofit.BaseRequestEntity
import com.ahuo.tools.network.retrofit.KKNetworkResponse

/**
 * Created on 17-5-12

 * @author liuhuijie
 */

class TestRequest(tag: String, requestCode: Int, kKNetworkResponse: KKNetworkResponse<*>, var account: String?, var password: String?) : BaseRequestEntity(tag, requestCode, kKNetworkResponse)
