package com.ahuo.personapp.base;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public interface IBasePresenter<T> {

    void setView(T view);

    void removeView(String tag);
}
