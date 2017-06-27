package com.ahuo.personapp.contract;

import com.ahuo.personapp.base.IBasePresenter;
import com.ahuo.personapp.base.IBaseView;
import com.ahuo.personapp.entity.response.GetUserResponse;
import com.ahuo.personapp.entity.response.LoginResponse;

/**
 * Created on 17-5-11
 *
 * @author liuhuijie
 */

public interface LoginContract {
    interface IView extends IBaseView {
        void loginSuccess(LoginResponse response);

        void getUsersSuccess(GetUserResponse response);

        void getUsersFail(String message);

        void loginFail(String message);
    }

    interface IPresenter extends IBasePresenter<IView> {
        void getUsers();

        void login(String account,String password);
    }

}
