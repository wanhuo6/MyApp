package com.ahuo.personapp.utils;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.ahuo.personapp.widget.LoadingDialogFragment;

/**
 * Created by hly on 16/7/29.
 * email hugh_hly@sina.cn
 */
public class LoadingUtils {
    private static LoadingDialogFragment sMLoadingDialogFragment;

    private static boolean isDialogShowing;

    /**
     * 显示加载提示框
     *
     * @param message 提示信息
     * @return
     */

    public static synchronized LoadingDialogFragment showLoadingDialog(FragmentActivity activity, String message) {
        if (!isDialogShowing) {
            sMLoadingDialogFragment = LoadingDialogFragment.createDialogFragment(message);
            sMLoadingDialogFragment.setCancelable(false);
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            ft.add(sMLoadingDialogFragment, "Loading");
            ft.commitAllowingStateLoss();
        } else {
            sMLoadingDialogFragment.setMessage(message);
        }
        isDialogShowing = true;
        return sMLoadingDialogFragment;
    }

    /**
     * 关闭加载提示框
     */
    public static synchronized void closeLoadingDialog() {
        if (sMLoadingDialogFragment != null) {
            sMLoadingDialogFragment.dismissAllowingStateLoss();
            if (sMLoadingDialogFragment.isDetached()) {
                sMLoadingDialogFragment.onDetach();
            }
            isDialogShowing = false;
        }
    }


    /**
     * 判断是否显示中
     *
     * @return
     */
    public static synchronized boolean isShowing() {
        return sMLoadingDialogFragment != null && sMLoadingDialogFragment.getDialog() != null && sMLoadingDialogFragment.getDialog().isShowing();
    }

}
