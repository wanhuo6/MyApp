package com.ahuo.personapp.ui.activity

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import com.ahuo.personapp.Presenter.RegisterPresenter
import com.ahuo.personapp.R
import com.ahuo.personapp.base.BaseActivity
import com.ahuo.personapp.contract.RegisterContract
import com.ahuo.tools.util.ToastUtils
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterContract.IView {
    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, RegisterActivity::class.java)
            activity.startActivity(intent)
        }
    }

    var mIPresenter: RegisterContract.IPresenter? = null


    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initData() {
        super.initData()
        mIPresenter = RegisterPresenter(TAG)
        mIPresenter!!.setView(this)
        tvRegister.setOnClickListener {
            toRegister()
        }

    }

    private fun toRegister() {
        val nicName=etNicName.text.toString().trim()
        val account = etAccount.text.toString().trim()
        val password = etPassword.text.toString().trim()
        if(TextUtils.isEmpty(nicName)){
            ToastUtils.showToast("昵称为空")
        }else if (TextUtils.isEmpty(account)) {
            ToastUtils.showToast("账号为空")
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast("密码为空")
        } else {
            showLoadingDialog(getString(R.string.loading_data_wait))
            mIPresenter!!.register(nicName,account, password)
        }
    }

    override fun registerSuccess(message: String) {
        dismissLoadingDialog()
        ToastUtils.showToast(message)
    }

    override fun registerFail(message: String) {
        dismissLoadingDialog()
        ToastUtils.showToast(message)
    }

}
