package com.ahuo.personapp.base;

import android.view.View;

import com.ahuo.personapp.R;
import com.ahuo.personapp.widget.MyAppBar;

import butterknife.BindView;

/**
 * Created on 17-5-11
 *
 * @author liuhuijie
 */

public abstract class BaseTitleActivity extends BaseActivity {


    @BindView(R.id.kk_toolbar)
    protected MyAppBar mKkToolbar;

    @BindView(R.id.split_line)
    View mSplitLine;


    @Override
    protected void initData() {
        super.initData();
        mKkToolbar.setTitleConfig(getTitleViewConfig());
        setSupportActionBar(mKkToolbar);

        //不显示Toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public abstract MyAppBar.TitleConfig getTitleViewConfig();

    /**
     * 1> 设置左边返回按钮的整件
     * 2> 设置标题文本
     *
     * @param title
     * @return
     */
    public MyAppBar.TitleConfig buildDefaultConfig(String title) {
        MyAppBar.TitleConfig config = new MyAppBar.TitleConfig(title);
        //config.leftViewListener = mBackOnClickListener;
        return config;
    }

    /**
     * 默认左侧按钮点击事件
     */
    public View.OnClickListener mBackOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };



}
