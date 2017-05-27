package com.ahuo.personapp.Presenter;

import com.ahuo.personapp.Biz.GetUsersBiz;
import com.ahuo.personapp.contract.LoginContract;
import com.ahuo.personapp.core.net.NetRequestCode;
import com.ahuo.personapp.entity.response.GetUserResponse;
import com.ahuo.tools.network.retrofit.BaseRequestEntity;
import com.ahuo.tools.network.retrofit.BaseResponseEntity;
import com.ahuo.tools.network.retrofit.KKNetworkResponse;
import com.ahuo.tools.util.MLog;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public class LoginPresenter implements LoginContract.IPresenter, KKNetworkResponse<BaseResponseEntity> {


    private GetUsersBiz mLoginBiz;

    private GetUsersBiz mGetUsersBiz;

    private LoginContract.IView mIView;

    private String mTag;

    public LoginPresenter(String tag) {
        this.mTag = tag;
    }

    @Override
    public void setView(LoginContract.IView view) {
        this.mIView = view;
    }

    @Override
    public void removeView(String tag) {
        this.mIView = null;
        if (mLoginBiz!=null){
            mLoginBiz.unSubscribe(tag);
            mLoginBiz=null;
        }
    }

    @Override
    public void getLogin() {
        if (mLoginBiz == null) {
            mLoginBiz = new GetUsersBiz();
        }
        mLoginBiz.execute(new BaseRequestEntity(mTag, NetRequestCode.LOGIN, this));
    }

    @Override
    public void onDataReady(BaseResponseEntity response) {
        MLog.e("sssssssssss" + response.requestCode);
        if (mIView == null) {
            return;
        }
        switch (response.requestCode) {
            case NetRequestCode.LOGIN:
                GetUserResponse loginResponse = (GetUserResponse) response;
                mIView.getUsers(loginResponse);

                break;
            default:

                break;


        }

    }

    @Override
    public void onDataError(int requestCode, int responseCode, String message) {
        MLog.e("sssssssssss" + requestCode+"=="+message);
        if (mIView == null) {
            return;
        }
        switch (requestCode) {
            case NetRequestCode.LOGIN:
                mIView.loginFail(message);
                break;

            default:

                break;


        }

    }


}
