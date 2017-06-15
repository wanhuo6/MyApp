package com.ahuo.personapp.base;

import com.ahuo.tools.network.retrofit.BaseResponseEntity;

/**
 * Created by ahuo on 17-5-27.
 */
public class BaseResponse<T> extends BaseResponseEntity{
    private T data;

    private String msg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    private boolean isSuccess;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;
}
