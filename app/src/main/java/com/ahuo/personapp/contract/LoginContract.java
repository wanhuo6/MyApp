package com.ahuo.personapp.contract;

import com.ahuo.personapp.base.IBasePresenter;
import com.ahuo.personapp.base.IBaseView;

/**
 * Created on 17-5-11
 *
 * @author liuhuijie
 */

public interface LoginContract {
    interface IView extends IBaseView {
        void loginSuccess();

        void loginFail(String message);
    }

    interface IPresenter extends IBasePresenter<IView> {
        void getLogin();
    }

}
