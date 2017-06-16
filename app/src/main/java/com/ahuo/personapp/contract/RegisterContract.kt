package com.ahuo.personapp.contract

import com.ahuo.personapp.base.IBasePresenter
import com.ahuo.personapp.base.IBaseView

/**
 * Created on 2017-6-16
 *
 * @author LiuHuiJie
 */
interface RegisterContract {

    interface IView : IBaseView {
        fun registerSuccess(message:String)
        fun registerFail(message:String)
    }

    interface IPresenter : IBasePresenter<IView> {
        fun register(nicName: String,account: String, password: String)
    }
}