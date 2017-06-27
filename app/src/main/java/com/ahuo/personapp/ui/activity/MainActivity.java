package com.ahuo.personapp.ui.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahuo.personapp.Presenter.LoginPresenter;
import com.ahuo.personapp.R;
import com.ahuo.personapp.base.BaseActivity;
import com.ahuo.personapp.contract.LoginContract;
import com.ahuo.personapp.entity.response.GetUserResponse;
import com.ahuo.personapp.entity.response.LoginResponse;
import com.ahuo.tools.imageloader.GlideLoaderUtil;
import com.ahuo.tools.util.ToastUtils;
import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;

import static com.ahuo.personapp.core.config.AppConfig.APP_LOGO;

public class MainActivity extends BaseActivity implements LoginContract.IView {

    @BindView(R.id.tv_register)
    TextView  mTvRegister;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.iv_user_photo)
    ImageView mIvUserPhoto;
    @BindView(R.id.tv_kotlin)
    TextView mTvKotlin;
    @BindView(R.id.tv_get_users)
    TextView mTvGetUsers;
    @BindView(R.id.tv_users)
    TextView mTvUsers;


    private static final String TAG = "MainActivity";

    private LoginContract.IPresenter mIPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initData() {
        super.initData();
        GlideLoaderUtil.loadNormalImage(this,APP_LOGO, GlideLoaderUtil.LOAD_IMAGE_DEFAULT_ID, mIvUserPhoto);
        mIPresenter = new LoginPresenter(TAG);
        mIPresenter.setView(this);

        mTvRegister.setOnClickListener(mClickListener);
        mTvLogin.setOnClickListener(mClickListener);
        mTvKotlin.setOnClickListener(mClickListener);
        mTvGetUsers.setOnClickListener(mClickListener);
    }


    @Override
    protected void onSingleClick(View view) {
        super.onSingleClick(view);
        switch (view.getId()) {
            case R.id.tv_register:
                RegisterActivity.Companion.startActivity(this);
                break;
            case R.id.tv_login:
                LoginActivity.Companion.startActivity(this);
                break;
            case R.id.tv_kotlin:
                PersonDataActivity.Companion.startActivity(this);
                break;
            case R.id.tv_get_users:
                showLoadingDialog(getString(R.string.loading_data_wait));
                mIPresenter.getUsers();
                break;
            default:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIPresenter.removeView(TAG);
    }

    @Override
    public void loginSuccess(LoginResponse loginResponse) {
        dismissLoadingDialog();


    }

    @Override
    public void getUsersSuccess(GetUserResponse response) {
        dismissLoadingDialog();
        mTvUsers.setText(JSONObject.toJSONString(response));
    }

    @Override
    public void getUsersFail(String message) {
        dismissLoadingDialog();
        if(!TextUtils.isEmpty(message)){
            ToastUtils.showToast(message);
        }
    }

    @Override
    public void loginFail(String message) {
        dismissLoadingDialog();
        if(!TextUtils.isEmpty(message)){
            ToastUtils.showToast(message);
        }
    }
}
