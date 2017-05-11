package com.ahuo.personapp.ui.activity;


import android.view.View;
import android.widget.TextView;

import com.ahuo.personapp.Presenter.LoginPresenter;
import com.ahuo.personapp.R;
import com.ahuo.personapp.base.BaseActivity;
import com.ahuo.personapp.contract.LoginContract;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements LoginContract.IView{

    @BindView(R.id.tv_login)
    TextView mTvLogin;


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
                mIPresenter.getLogin();
                break;
            default:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFail(String message) {

    }
}
