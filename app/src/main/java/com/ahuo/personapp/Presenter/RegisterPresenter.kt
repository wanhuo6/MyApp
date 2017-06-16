package com.ahuo.personapp.Presenter

import com.ahuo.personapp.Biz.RegisterBiz
import com.ahuo.personapp.contract.RegisterContract
import com.ahuo.personapp.core.net.NetRequestCode
import com.ahuo.personapp.entity.request.RegisterRequest
import com.ahuo.personapp.entity.response.RegisterResponse
import com.ahuo.tools.network.retrofit.BaseResponseEntity
import com.ahuo.tools.network.retrofit.KKNetworkResponse

/**
 * Created on 2017-6-16
 *
 * @author LiuHuiJie
 */
class RegisterPresenter(private var mTag:String) : RegisterContract.IPresenter, KKNetworkResponse<BaseResponseEntity> {

    var mIView: RegisterContract.IView? = null
    var mRegisterBiz: RegisterBiz? = null

    override fun setView(view: RegisterContract.IView?) {
        mIView = view
    }

    override fun removeView(tag: String?) {
        mIView = null
    }

    override fun register(nicName: String,account: String, password: String) {
        if (mRegisterBiz == null) {
            mRegisterBiz = RegisterBiz()
        }
        mRegisterBiz!!.execute(RegisterRequest(mTag, NetRequestCode.REGISTER, this,nicName, account, password))
    }

    override fun onDataError(requestCode: Int, responseCode: Int, message: String?) {
        if (mIView == null) {
            return
        }
        when (requestCode) {
            NetRequestCode.REGISTER -> {
                    mIView!!.registerFail(message!!)
            }

        }
    }

    override fun onDataReady(response: BaseResponseEntity?) {
        if (mIView == null) {
            return
        }
        when (response!!.requestCode) {
            NetRequestCode.REGISTER -> {
                var registerResponse = response as RegisterResponse
                if (registerResponse.isSuccess){
                    mIView!!.registerSuccess(registerResponse.url!!)
                }else{
                    mIView!!.registerFail(registerResponse.msg)
                }
            }

        }

    }
}
