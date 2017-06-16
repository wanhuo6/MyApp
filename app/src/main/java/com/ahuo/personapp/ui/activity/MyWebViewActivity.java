package com.ahuo.personapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.ahuo.personapp.R;
import com.ahuo.personapp.base.BaseActivity;
import com.ahuo.tools.util.ToastUtils;

/**
 * Created on 2017-6-16
 *
 * @author LiuHuiJie
 */
public class MyWebViewActivity extends BaseActivity {
    private WebView webview;
    private WebSettings settings;
    private SwipeRefreshLayout swipe_refresh;

    private String mUrl = null;

    private final static String INTENT_URL="intent_url";

    public static void startActivity(Activity activity, String url){

        Intent intent=new Intent(activity,MyWebViewActivity.class);
        intent.putExtra(INTENT_URL,url);
        activity.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_web;
    }


    @Override
    protected void initData() {
        super.initData();
        swipe_refresh = (SwipeRefreshLayout) this.findViewById(R.id.swipe_refresh);


        webview = new WebView(getApplicationContext());
        webview.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        swipe_refresh.addView(webview);
        settings = webview.getSettings();
        webview.setWebViewClient(new MyWebViewClient());
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webview.loadUrl(webview.getUrl());
            }
        });
        //设置进度条
        webview.setWebChromeClient(new WebChromeClient() {
                                       @Override
                                       public void onProgressChanged(WebView view, int newProgress) {
                                           if (newProgress == 100) {
                                               //隐藏进度条
                                               swipe_refresh.setRefreshing(false);
                                           } else {
                                               if (!swipe_refresh.isRefreshing())
                                                   swipe_refresh.setRefreshing(true);
                                           }

                                           super.onProgressChanged(view, newProgress);
                                       }

                                       @Override
                                       public void onReceivedTitle(WebView view, String title) {
                                           super.onReceivedTitle(view, title);
                                       }
                                   }
        );
        if(getIntent()!=null){
            mUrl = getIntent().getStringExtra(INTENT_URL);
        }
        if (TextUtils.isEmpty(mUrl)) {
           ToastUtils.showToast("网络异常");
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        settings.setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webview.loadUrl(mUrl);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        settings.setJavaScriptEnabled(false);
    }

    @Override
    protected void onDestroy() {
        swipe_refresh.removeAllViews();
        webview.stopLoading();
        webview.removeAllViews();
        webview.destroy();
        webview = null;
        swipe_refresh = null;
        super.onDestroy();
    }


    private final class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url2) {
            view.loadUrl(url2);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //view.loadUrl(url);
            // pb_loading.setVisibility(IView.GONE);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }
}
