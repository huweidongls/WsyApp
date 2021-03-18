package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetComboByIdBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LcTaocanDetailsActivity extends BaseActivity {

    private Context context = LcTaocanDetailsActivity.this;

    @BindView(R.id.iv_agree)
    ImageView ivAgree;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.webview)
    WebView webview;

    private boolean isAgree = false;

    private String id = "";
    private GetComboByIdBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lc_taocan_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(LcTaocanDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(LcTaocanDetailsActivity.this);
        initData();
        initWebView();

    }

    private void initWebView() {

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setTextZoom(100);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
//        webview.getSettings().setSupportZoom(true);
//        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setCacheMode(
                webview.getSettings().LOAD_NO_CACHE); // 缓存设置
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //使用控件ProgressDialog来显示更新进度条示数
//                if (newProgress == 100) {
//                    progressBar.setVisibility(View.GONE);
//                } else {
//                    progressBar.setVisibility(View.VISIBLE);
//                    progressBar.setProgress(newProgress);
//                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受所有网站的证书
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //加载错误时的回调
            }
        });
        Logger.e("123123", id);
        String url = NetUrl.H5BASE_URL+"/wap/combo/v1.0.0/getComboDetailPage/"+id;
        webview.loadUrl(url);

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("comboId", id);
        ViseUtil.Post(context, NetUrl.getComboById, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                bean = gson.fromJson(s, GetComboByIdBean.class);
                tvTitle.setText(bean.getData().getComboName());
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.iv_agree, R.id.ll_buy, R.id.ll_xieyi})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_agree:
                isAgree = true;
                ivAgree.setImageResource(R.mipmap.duihao_xieyi);
                break;
            case R.id.ll_buy:
                if(isAgree){
//                    DialogBuy dialogBuy = new DialogBuy(context);
//                    dialogBuy.show();
                    intent.putExtra("id", bean.getData().getId()+"");
                    intent.putExtra("type", "1");
                    intent.setClass(context, TaocanShebeiActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtil.showShort(context, "请勾选乐橙云服务协议");
                }
                break;
            case R.id.ll_xieyi:
                isAgree = true;
                ivAgree.setImageResource(R.mipmap.duihao_xieyi);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (webview != null) {
            //再次打开页面时，若界面没有消亡，会导致进度条不显示并且界面崩溃
            webview.stopLoading();
            webview.onPause();
            webview.clearCache(true);
            webview.clearHistory();
            //动态创建webview调用
            //ViewGroup parent = (ViewGroup) mWebView.getParent();
            //if (parent != null) {
            //  parent.removeView(mWebView);
            //}
            webview.removeAllViews();
            //先结束未结束线程，以免可能会导致空指针异常
            webview.destroy();
            webview = null;
            super.onDestroy();
        }
    }

}
