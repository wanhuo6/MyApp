package com.kk.http.download;


import android.util.Log;

import com.kk.database.model.DownLoadModel;
import com.kk.http.retrofit.KKNetWorkRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by hly on 16/4/11.
 * email hugh_hly@sina.cn
 */
public class DownLoadTask implements Runnable {

    private final String TAG = DownLoadTask.class.getSimpleName();

    private int mTaskId;

    private String mSaveFileName;

    private DownLoadTaskListener mDownLoadTaskListener;

    private DownLoadService mApiService = KKNetWorkRequest.getInstance().getDownLoadService();

    private Call<ResponseBody> mResponseCall;

    private long mFileSizeDownloaded;

    private DownLoadModel mDownLoadModel;

    private long mNeedDownSize;

    private final long CALL_BACK_LENGTH = 1024 * 1024;

    DownLoadTask(int taskId, DownLoadModel downLoadModel, DownLoadTaskListener downLoadTaskListener) {
        this.mDownLoadModel = downLoadModel;
        this.mDownLoadTaskListener = downLoadTaskListener;
        this.mSaveFileName = downLoadModel.saveName;
        this.mTaskId = taskId;
        this.mNeedDownSize = downLoadModel.end - (downLoadModel.start + downLoadModel.downed);
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        if (mDownLoadModel.downed != 0) {
            mResponseCall = mApiService.downloadFile(mDownLoadModel.url, "bytes=" + (mDownLoadModel.downed + mDownLoadModel.start) + "-" + mDownLoadModel.end);
        } else {
            mResponseCall = mApiService.downloadFile(mDownLoadModel.url, "bytes=" + mDownLoadModel.start + "-" + mDownLoadModel.end);
        }
        ResponseBody result = null;
        try {
            Response response = mResponseCall.execute();
            //onStart();
            result = (ResponseBody) response.body();
            if (response.isSuccessful()) {
                if (writeToFile(result, mDownLoadModel.start, mDownLoadModel.downed)) {
                    onCompleted();
                }
            } else {
                onError(new Throwable(response.message()));
            }
        } catch (IOException e) {
            onError(new Throwable(e.getMessage()));
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    private boolean writeToFile(ResponseBody body, long startSet, long mDownedSet) {
        try {
            File futureStudioIconFile = new File(mSaveFileName);

            if (!futureStudioIconFile.exists()) {
                futureStudioIconFile.createNewFile();
            }

            RandomAccessFile oSavedFile = new RandomAccessFile(futureStudioIconFile, "rw");

            oSavedFile.seek(startSet + mDownedSet);

            InputStream inputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                mFileSizeDownloaded = 0;

                inputStream = body.byteStream();

                while (mFileSizeDownloaded < mNeedDownSize) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }
                    oSavedFile.write(fileReader, 0, read);

                    mFileSizeDownloaded += read;

                    mDownLoadModel.downed += read;

                    if (mFileSizeDownloaded >= CALL_BACK_LENGTH) {
                        onDownLoading(mFileSizeDownloaded);
                        mNeedDownSize -= mFileSizeDownloaded;
                        mFileSizeDownloaded = 0;
                    } else {
                        if (mNeedDownSize < CALL_BACK_LENGTH) {
                            if (mFileSizeDownloaded - 1 == mNeedDownSize) {
                                onDownLoading(mFileSizeDownloaded);
                                break;
                            } else if (mFileSizeDownloaded - 1 > mNeedDownSize) {
                                onDownLoading(mNeedDownSize);
                                mDownLoadModel.downed = (mDownLoadModel.end - mDownLoadModel.start) + 1;
                                break;
                            }
                        }
                    }
                }
                return true;
            } finally {
                Log.d("abc", Thread.currentThread().getState().toString());
                oSavedFile.close();

                if (inputStream != null) {
                    inputStream.close();
                }

                if (body != null) {
                    body.close();
                }
            }
        } catch (IOException e) {
            if (e instanceof SocketTimeoutException) {
                if (mFileSizeDownloaded > 0) {
                    onDownLoading(mFileSizeDownloaded);
                    mFileSizeDownloaded = 0;
                }
                onError(e);
            } else if (e instanceof InterruptedIOException) {
                onCancel();
            } else {
                if (mFileSizeDownloaded > 0) {
                    onDownLoading(mFileSizeDownloaded);
                    mFileSizeDownloaded = 0;
                }
                onError(e);
            }
            return false;
        }
    }

//    private void onStart() {
//        mDownLoadTaskListener.onStart();
//    }

    private void onCancel() {
        mResponseCall.cancel();
        mResponseCall = null;
        mDownLoadTaskListener.onCancel(mDownLoadModel);
    }

    private void onCompleted() {
        mResponseCall = null;
        mDownLoadTaskListener.onCompleted(mDownLoadModel);
    }

    private void onDownLoading(long downSize) {
        mDownLoadTaskListener.onDownLoading(downSize);
    }

    private void onError(Throwable throwable) {
        mDownLoadTaskListener.onError(mDownLoadModel, throwable);
    }

    public static final class Builder {
        private DownLoadModel mDownModel;

        private int mTaskId;

        private DownLoadTaskListener mDownLoadTaskListener;

        public Builder downLoadModel(DownLoadModel downLoadModel) {
            mDownModel = downLoadModel;
            return this;
        }

        public Builder downLoadTaskListener(DownLoadTaskListener downLoadTaskListener) {
            mDownLoadTaskListener = downLoadTaskListener;
            return this;
        }

        public Builder taskId(int id) {
            this.mTaskId = id;
            return this;
        }

        public DownLoadTask build() {
            if (mDownModel.url.isEmpty()) {
                throw new IllegalStateException("DownLoad URL required.");
            }

            if (mDownLoadTaskListener == null) {
                throw new IllegalStateException("DownLoadTaskListener required.");
            }

            if (mDownModel.end == 0) {
                throw new IllegalStateException("SetCount required.");
            }

            if (mTaskId == 0) {
                throw new IllegalStateException("TaskId required.");
            }
            return new DownLoadTask(mTaskId, mDownModel, mDownLoadTaskListener);
        }
    }
}
