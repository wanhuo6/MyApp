package com.kk.http.config;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by My Zhihua on 2016/4/19.
 * Description : 用来转换发送给服务器的类
 */
public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/octet-stream");

    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value));
    }
}
