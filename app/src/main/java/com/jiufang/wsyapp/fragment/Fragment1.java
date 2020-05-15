package com.jiufang.wsyapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.IndexAdapter;
import com.jiufang.wsyapp.base.LazyFragment;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.ui.LoginActivity;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.zxing.activity.CaptureActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.videogo.exception.BaseException;
import com.videogo.util.LocalValidate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2020/4/27.
 */

public class Fragment1 extends LazyFragment {

    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.ll_yes)
    LinearLayout llYes;
    @BindView(R.id.ll_no)
    LinearLayout llNo;

    private IndexAdapter adapter;
    private List<String> mList;

    private int lastPostion;

    private LocalValidate mLocalValidate = null;
    private String mSerialNoStr = null;
    private String mSerialVeryCodeStr = null;
    private String deviceType = null;

    private String devType="";

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment1;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        if(SpUtils.getUserId(getContext()).equals("0")){
            llNo.setVisibility(View.VISIBLE);
            llYes.setVisibility(View.GONE);
        }else {
            llNo.setVisibility(View.GONE);
            llYes.setVisibility(View.VISIBLE);
            initData();
        }
    }

    private void initData() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(500);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(500);
            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "10");
        map.put("userId", SpUtils.getUserId(getContext()));
        ViseUtil.Post(getContext(), NetUrl.getBindDeviceList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {

            }
        });

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new IndexAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.rl_left, R.id.rl_right, R.id.btn_login})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_left:
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
                    lastPostion = ((GridLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }else {
                    lastPostion = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                }
                recyclerView.setAdapter(adapter);
                recyclerView.scrollToPosition(lastPostion);
                break;
            case R.id.rl_right:
                startActivityForResult(new Intent(getContext(), CaptureActivity.class), 1001);
                break;
            case R.id.btn_login:
                intent.setClass(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            jiexiYs(result);
//            String devSn="";
//            if (result.contains(",")) {
//                devSn = result.split(",")[0].split(":")[1];
//            }else if(result.contains(":")){
//                devSn=result.split(":")[0];
//                devType=result.split(":")[1];
//            }
//            if(devSn!=null&&devSn.length()!=15){
//                devSn= result.substring(result.indexOf(":")+1,result.indexOf("}"));
//            }
//            mSnText.setText(devSn);
        }
    }

    private void jiexiYs(String resultString) {
        //ys7C78047095TQJATVCS-C1HC-1D1WFR
        // 初始化数据
        mSerialNoStr = "";
        mSerialVeryCodeStr = "";
        deviceType = "";
        Log.e("123123", "resultString = " + resultString);
        // CS-F1-1WPFR
        // CS-A1-1WPFR
        // CS-C1-1FPFR
        // resultString = "www.xxx.com\n456654855\nABCDEF\nCS-C3-21PPFR\n";
        // 字符集合
        String[] newlineCharacterSet = {
                "\n\r", "\r\n", "\r", "\n"};
        String stringOrigin = resultString;
        // 寻找第一次出现的位置
        int a = -1;
        int firstLength = 1;
        for (String string : newlineCharacterSet) {
            if (a == -1) {
                a = resultString.indexOf(string);
                if (a > stringOrigin.length() - 3) {
                    a = -1;
                }
                if (a != -1) {
                    firstLength = string.length();
                }
            }
        }

        // 扣去第一次出现回车的字符串后，剩余的是第二行以及以后的
        if (a != -1) {
            resultString = resultString.substring(a + firstLength);
        }
        // 寻找最后一次出现的位置
        int b = -1;
        for (String string : newlineCharacterSet) {
            if (b == -1) {
                b = resultString.indexOf(string);
                if (b != -1) {
                    mSerialNoStr = resultString.substring(0, b);
                    firstLength = string.length();
                }
            }
        }

        // 寻找遗失的验证码阶段
        if (mSerialNoStr != null && b != -1 && (b + firstLength) <= resultString.length()) {
            resultString = resultString.substring(b + firstLength);
        }

        // 再次寻找回车键最后一次出现的位置
        int c = -1;
        for (String string : newlineCharacterSet) {
            if (c == -1) {
                c = resultString.indexOf(string);
                if (c != -1) {
                    mSerialVeryCodeStr = resultString.substring(0, c);
                }
            }
        }

        // 寻找CS-C2-21WPFR 判断是否支持wifi
        if (mSerialNoStr != null && c != -1 && (c + firstLength) <= resultString.length()) {
            resultString = resultString.substring(c + firstLength);
        }
        if (resultString != null && resultString.length() > 0) {
            deviceType = resultString;
        }

        if (b == -1) {
            mSerialNoStr = resultString;
        }

        if (mSerialNoStr == null) {
            mSerialNoStr = stringOrigin;
        }
        Log.e("123123", "mSerialNoStr = " + mSerialNoStr + ",mSerialVeryCodeStr = " + mSerialVeryCodeStr
                + ",deviceType = " + deviceType);

    }

//    private void isValidate() {
//        mLocalValidate = new LocalValidate();
//        try {
//            mLocalValidate.localValidatSerialNo(mSerialNoStr);
//            LogUtil.infoLog(TAG, mSerialNoStr);
//        } catch (BaseException e) {
//            handleLocalValidateSerialNoFail(e.getErrorCode());
//            LogUtil.errorLog(TAG, "searchCameraBySN-> local validate serial no fail, errCode:" + e.getErrorCode());
//            return;
//        }
//
//        if (!ConnectionDetector.isNetworkAvailable(this)) {
//            showToast(R.string.query_camera_fail_network_exception);
//            return;
//        }
//        Bundle bundle = new Bundle();
//        bundle.putInt("type", 1);
//        bundle.putString("SerialNo", mSerialNoStr);
//        bundle.putString("very_code", mSerialVeryCodeStr);
//        bundle.putString(AutoWifiNetConfigActivity.DEVICE_TYPE, deviceType);
//        Log.e("123123", "SerialNo:" + mSerialNoStr);
//        Log.e("123123", "very_code:" + mSerialVeryCodeStr);
//        LogUtil.debugLog(TAG, "very_code:" + mSerialVeryCodeStr);
//        Intent intent = new Intent(CaptureActivity.this, SeriesNumSearchActivity.class);
//        intent.putExtras(bundle);
//        CaptureActivity.this.startActivity(intent);
//
//        mScanNow = false;
//    }

    private boolean isDeviceQRCode(String qrCode) {
        // 字符集合
        String[] newlineCharacterSet = {
                "\n\r", "\r\n", "\r", "\n"};
        String[] tempStr;
        for (String sp : newlineCharacterSet) {
            tempStr = qrCode.split(sp);
            if (tempStr != null && tempStr.length >= 2) {
                try {
                    mLocalValidate.localValidatSerialNo(tempStr[1]);
                    return true;
                } catch (BaseException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return false;
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
    }

}
