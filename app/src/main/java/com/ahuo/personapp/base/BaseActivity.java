package com.ahuo.personapp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.ahuo.personapp.utils.MyClickListener;

import butterknife.ButterKnife;

/**
 * Created on 17-5-10
 *
 * @author liuhuijie
 */

public abstract class BaseActivity extends AppCompatActivity {



    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        initWindows();

        setContentView(getLayoutId());

        ButterKnife.bind(this);

        initData();
    }

    protected void initWindows() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    protected void initData() {
    }


    protected abstract int getLayoutId();
    protected MyClickListener mClickListener = new MyClickListener() {
        @Override
        protected void onKKClick(View v) {
            onSingleClick(v);
        }
    };

    protected void onSingleClick(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }




}
