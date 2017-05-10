package com.kk.http.download;

import com.kk.database.model.DownLoadModel;

/**
 * Created by hly on 16/4/11.
 * email hugh_hly@sina.cn
 */
public interface DownLoadTaskListener {
    void onStart();

    void onCancel(DownLoadModel downLoadModel);

    void onDownLoading(long downSize);

    void onCompleted(DownLoadModel downLoadModel);

    void onError(DownLoadModel downLoadModel, Throwable throwable);
}
