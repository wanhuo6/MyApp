package com.ahuo.personapp.ui.activity;

import android.app.Activity;
import android.content.Intent;

import com.ahuo.personapp.Presenter.LoginPresenter;
import com.ahuo.personapp.R;
import com.ahuo.personapp.base.BaseActivity;
import com.ahuo.personapp.contract.LoginContract;
import com.ahuo.personapp.entity.response.GetUserResponse;
import com.ahuo.personapp.entity.response.LoginResponse;

/**
 * Created on 2017-6-15
 *
 * @author LiuHuiJie
 */
public class TestActivity extends BaseActivity implements LoginContract.IView{

    private LoginContract.IPresenter mIPresenter;

    public static void startActivity(Activity activity){
        Intent intent=new Intent(activity,TestActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        mIPresenter=new LoginPresenter("dd");
        mIPresenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void loginSuccess(LoginResponse response) {

    }

    @Override
    public void getUsersSuccess(GetUserResponse response) {

    }

    @Override
    public void loginFail(String message) {

    }
}
