package com.ahuo.personapp.Presenter;

import com.ahuo.personapp.Biz.GetUsersBiz;
import com.ahuo.personapp.Biz.LoginBiz;
import com.ahuo.personapp.contract.LoginContract;
import com.ahuo.personapp.core.net.NetRequestCode;
import com.ahuo.personapp.entity.request.LoginRequest;
import com.ahuo.personapp.entity.response.GetUserResponse;
import com.ahuo.personapp.entity.response.LoginResponse;
import com.ahuo.tools.network.retrofit.BaseRequestEntity;
import com.ahuo.tools.network.retrofit.BaseResponseEntity;
import com.ahuo.tools.network.retrofit.KKNetworkResponse;
import com.ahuo.tools.util.MLog;
import com.ahuo.tools.util.ToastUtils;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public class LoginPresenter implements LoginContract.IPresenter, KKNetworkResponse<BaseResponseEntity> {


    private GetUsersBiz mGetUsersBiz;

    private LoginBiz mLoginBiz;

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
        if (mGetUsersBiz!=null){
            mGetUsersBiz.unSubscribe(tag);
            mGetUsersBiz=null;
        }
    }

    @Override
    public void getUsers() {
        if (mGetUsersBiz == null) {
            mGetUsersBiz = new GetUsersBiz();
        }
        mGetUsersBiz.execute(new BaseRequestEntity(mTag, NetRequestCode.GET_USERS, this));
    }

    @Override
    public void login(String account, String password) {
       if (mLoginBiz==null){
           mLoginBiz=new LoginBiz();
       }
       mLoginBiz.execute(new LoginRequest(mTag,NetRequestCode.LOGIN,this,account,password));
    }

    @Override
    public void onDataReady(BaseResponseEntity response) {
        MLog.e("sssssssssss" + response.requestCode);
        if (mIView == null) {
            return;
        }
        switch (response.requestCode) {
            case NetRequestCode.GET_USERS:
                GetUserResponse getUserResponse = (GetUserResponse) response;
                mIView.getUsersSuccess(getUserResponse);
                break;
            case NetRequestCode.LOGIN:
                LoginResponse loginResponse= (LoginResponse) response;
                if (loginResponse.isSuccess()){
                    mIView.loginSuccess(loginResponse);
                }else{
                    mIView.loginFail(loginResponse.getMsg());
                }


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
