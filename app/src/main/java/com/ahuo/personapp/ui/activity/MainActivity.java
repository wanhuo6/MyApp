package com.ahuo.personapp.ui.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahuo.personapp.Presenter.LoginPresenter;
import com.ahuo.personapp.R;
import com.ahuo.personapp.base.BaseActivity;
import com.ahuo.personapp.contract.LoginContract;
import com.ahuo.personapp.entity.response.LoginResponse;
import com.kk.tool.imageloader.GlideLoaderUtil;
import com.kk.tool.util.ToastUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements LoginContract.IView{

    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.iv_user_photo)
    ImageView mIvUserPhoto;


    private static final String TAG = "MainActivity";

    private LoginContract.IPresenter mIPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initData() {
        super.initData();
        mIPresenter=new LoginPresenter(TAG);
        mIPresenter.setView(this);

        mTvLogin.setOnClickListener(mClickListener);
    }

    @Override
    protected void onSingleClick(View view) {
        super.onSingleClick(view);
        switch (view.getId()){
            case R.id.tv_login:
                showLoadingDialog(getString(R.string.loading_data_wait));
                mIPresenter.getLogin();
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
        GlideLoaderUtil.loadFullWidthImage(this,loginResponse.photoUrl,GlideLoaderUtil.LOAD_IMAGE_DEFAULT_ID,mIvUserPhoto);

    }

    @Override
    public void loginFail(String message) {
        dismissLoadingDialog();
        if (TextUtils.isEmpty(message)){
            return;
        }
        ToastUtils.showToast(message);
    }
}
