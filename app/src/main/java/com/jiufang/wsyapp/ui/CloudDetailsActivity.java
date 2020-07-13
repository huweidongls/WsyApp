package com.jiufang.wsyapp.ui;

import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetComboOrderByDeviceIdBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CloudDetailsActivity extends BaseActivity {

    private Context context = CloudDetailsActivity.this;

    @BindView(R.id.iv_agree)
    ImageView ivAgree;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_taocan_leixing)
    TextView tvTaocanLeixing;
    @BindView(R.id.tv_taocan_type)
    TextView tvTaocanType;
    @BindView(R.id.tv_taocan_time)
    TextView tvTaocanTime;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_weikaitong)
    ImageView ivWeikaitong;
    @BindView(R.id.ll_bg)
    LinearLayout llBg;
    @BindView(R.id.webview)
    WebView webview;

    private boolean isXieyi = false;

    private String type = "1";//1乐橙  2萤石
    private String id = "";

    private int comboIsOpen = 0;

    private int mYear;
    private int mMonth;
    private int mDay;

    private String time = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_details);

        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        StatusBarUtils.setStatusBar(CloudDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(CloudDetailsActivity.this);
        initData();

    }

    private void initWebView(boolean b) {

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
        String url = "";
        if(b){
            url = NetUrl.H5BASE_URL+"/wap/combo/v1.0.0/getComboDetailByDeviceId/"+id;
        }else {
            if(type.equals("1")){
                url = NetUrl.H5BASE_URL+"/wap/combo/v1.0.0/getLcStorageIntroduce";
            }else if(type.equals("2")){
                url = NetUrl.H5BASE_URL+"/wap/combo/v1.0.0/getYsStorageIntroduce";
            }
        }
        webview.loadUrl(url);

    }

    private void initData() {

        if(type.equals("1")){
            llBg.setBackgroundResource(R.mipmap.bg_lc_taocan_details);
        }else if(type.equals("2")){
            llBg.setBackgroundResource(R.mipmap.bg_ys_taocan_details);
        }

        time = mYear+"-"+ StringUtils.getBuling(mMonth+1)+"-"+StringUtils.getBuling(mDay);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getComboOrderByDeviceId, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetComboOrderByDeviceIdBean bean = gson.fromJson(s, GetComboOrderByDeviceIdBean.class);
                if(bean.getData()!=null){
                    initWebView(true);
                    tv.setVisibility(View.VISIBLE);
                    view.setVisibility(View.VISIBLE);
                    ll1.setVisibility(View.VISIBLE);
                    if(type.equals("2")){
                        ll.setVisibility(View.VISIBLE);
                    }
                    ivWeikaitong.setVisibility(View.GONE);
                    comboIsOpen = bean.getData().getComboIsOpen();
                    if(comboIsOpen == 0){
                        iv.setImageResource(R.mipmap.turn_off);
                    }else if(comboIsOpen == 1){
                        iv.setImageResource(R.mipmap.turn_on);
                    }
                    tvTaocanLeixing.setText(bean.getData().getComboCategoryName());
                    int i = StringUtils.getTimeCompareSize(time, bean.getData().getComboExpireTime());
                    if(i == 3){
                        tvTaocanType.setText("使用中");
                    }else {
                        tvTaocanType.setText("已过期");
                    }
                    tvTaocanTime.setText(bean.getData().getComboStartTime()+"~"+bean.getData().getComboExpireTime());
                }else {
                    initWebView(false);
                    ivWeikaitong.setVisibility(View.VISIBLE);
                    if(type.equals("1")){
                        ivWeikaitong.setImageResource(R.mipmap.lc_weikaitong);
                    }else if(type.equals("2")){
                        ivWeikaitong.setImageResource(R.mipmap.ys_weikaitong);
                    }
                }
            }

            @Override
            public void onElse(String s) {
                Logger.e("123123", s);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.ll_xieyi, R.id.ll_buy, R.id.iv})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.iv:
                if(comboIsOpen == 0){
                    setComboIsOpen(1);
                }else if(comboIsOpen == 1){
                    setComboIsOpen(0);
                }
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_xieyi:
                if(!isXieyi){
                    isXieyi = true;
                    ivAgree.setImageResource(R.mipmap.duihao_xieyi);
                }
                break;
            case R.id.ll_buy:
                if(isXieyi){
                    if(type.equals("1")){
                        intent.setClass(context, LcTaocanActivity.class);
                        startActivity(intent);
                    }else {
                        intent.setClass(context, YsTaocanActivity.class);
                        startActivity(intent);
                    }
                }else {
                    ToastUtil.showShort(context, "请勾选协议");
                }
                break;
        }
    }

    /**
     * 设置套餐开关
     * @param i
     */
    private void setComboIsOpen(int i) {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("status", i+"");
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setYsCloudStorageEnable, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                if(i == 0){
                    comboIsOpen = 0;
                    iv.setImageResource(R.mipmap.turn_off);
                }else if(i == 1){
                    comboIsOpen = 1;
                    iv.setImageResource(R.mipmap.turn_on);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

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
