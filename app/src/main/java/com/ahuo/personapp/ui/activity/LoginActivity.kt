package com.ahuo.personapp.ui.activity

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import com.ahuo.personapp.Presenter.LoginPresenter
import com.ahuo.personapp.R
import com.ahuo.personapp.base.BaseActivity
import com.ahuo.personapp.contract.LoginContract
import com.ahuo.personapp.entity.response.GetUserResponse
import com.ahuo.personapp.entity.response.LoginResponse
import com.ahuo.tools.util.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.IView {

    var mIPresenter: LoginContract.IPresenter? = null

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initData() {
        super.initData()
        mIPresenter = LoginPresenter(TAG)
        mIPresenter!!.setView(this)
        tvLogin.setOnClickListener {
            toLogin()
        }
    }

    private fun toLogin() {
        val account = etAccount.text.toString().trim()
        val password = etPassword.text.toString().trim()
        if (TextUtils.isEmpty(account)) {
            ToastUtils.showToast("账号为空")
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast("密码为空")
        } else {
            showLoadingDialog(getString(R.string.loading_data_wait))
            mIPresenter!!.login(account, password)
        }
    }

    override fun loginSuccess(response: LoginResponse?) {
        dismissLoadingDialog()
        ToastUtils.showToast(response!!.url)
        MyWebViewActivity.startActivity(this,response!!.url)
        finish()
    }

    override fun getUsersSuccess(response: GetUserResponse?) {

    }

    override fun loginFail(message: String?) {
        dismissLoadingDialog()
        ToastUtils.showToast(message)
    }


}
