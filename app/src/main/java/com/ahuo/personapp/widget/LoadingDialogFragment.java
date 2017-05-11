package com.ahuo.personapp.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ahuo.personapp.R;

import java.lang.ref.WeakReference;


/**
 * Created by hly on 16/5/6.
 * email hugh_hly@sina.cn
 */
public class LoadingDialogFragment extends DialogFragment {

    private String mMessage;
    private static final String KEY_MESSAGE = "key_message";

    private TextView mTvMessage;

    private MyHandler mMyHandler = new MyHandler(this);

    public static LoadingDialogFragment createDialogFragment(String message) {
        LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        loadingDialogFragment.setArguments(bundle);
        return loadingDialogFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessage = getArguments().getString(KEY_MESSAGE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.LoadingDialogStyle);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_loading, null);
        mTvMessage = (TextView) view.findViewById(R.id.tv_msg);
        mTvMessage.setText(mMessage);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        int width = (int) (getActivity().getResources().getDisplayMetrics().widthPixels * 0.8);
        getDialog().getWindow().setLayout(width, getDialog().getWindow().getAttributes().height);
    }

    public void setMessage(String message) {
        Message message1 = mMyHandler.obtainMessage();
        message1.obj = message;
        mMyHandler.sendMessage(message1);
    }

    class MyHandler extends Handler {
        private final WeakReference<LoadingDialogFragment> mActivity;

        MyHandler(LoadingDialogFragment activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LoadingDialogFragment activity = mActivity.get();
            if (activity != null && activity.getActivity() != null) {
                mTvMessage.setText(String.valueOf(msg.obj));
            }
        }
    }
}
