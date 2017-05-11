package com.ahuo.personapp.Presenter;

import android.util.Log;

import com.ahuo.personapp.Biz.LoginBiz;
import com.ahuo.personapp.contract.LoginContract;
import com.ahuo.personapp.core.net.NetRequestCode;
import com.kk.http.retrofit.BaseRequestEntity;
import com.kk.http.retrofit.BaseResponseEntity;
import com.kk.http.retrofit.KKNetworkResponse;
import com.kk.tool.util.MLog;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public class LoginPresenter implements LoginContract.IPresenter,KKNetworkResponse<BaseResponseEntity>{


    private LoginBiz mLoginBiz;

    private LoginContract.IView mIView;

    private String mTag;

    public LoginPresenter(String tag){
        this.mTag=tag;
    }

    @Override
    public void setView(LoginContract.IView view) {
       this.mIView=view;
    }

    @Override
    public void removeView(String tag) {
       this.mTag=tag;
    }

    @Override
    public void getLogin() {
         if (mLoginBiz==null){
             mLoginBiz=new LoginBiz();
         }
         mLoginBiz.execute(new BaseRequestEntity(mTag, NetRequestCode.LOGIN,this));
    }

    @Override
    public void onDataReady(BaseResponseEntity response) {
        MLog.e("sssssssssss"+response.requestCode);

    }

    @Override
    public void onDataError(int requestCode, int responseCode, String message) {
        MLog.e("sssssssssss"+responseCode+"==="+message);
        Log.d(mTag, "onDataError: "+message+responseCode);
    }


}
