package com.jiufang.wsyapp.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.IndexAdapter;
import com.jiufang.wsyapp.adapter.IndexGridAdapter;
import com.jiufang.wsyapp.base.LazyFragment;
import com.jiufang.wsyapp.bean.GetBindDeviceListBean;
import com.jiufang.wsyapp.bean.GetBindDeviceStatusInfoBean;
import com.jiufang.wsyapp.dialog.DialogMsgDelete;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.ui.IndexSetActivity;
import com.jiufang.wsyapp.ui.LoginActivity;
import com.jiufang.wsyapp.ui.MsgLcShebeiListActivity;
import com.jiufang.wsyapp.ui.MsgYsShebeiListActivity;
import com.jiufang.wsyapp.ui.SearchActivity;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
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
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.lang.reflect.Method;
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
    @BindView(R.id.rv_grid)
    RecyclerView rvGrid;

    private IndexAdapter adapter;
    private List<GetBindDeviceListBean.DataBean.RecordsBean> mList;

    private IndexGridAdapter adapterGrid;
    private List<GetBindDeviceListBean.DataBean.RecordsBean> mListGrid;

    private int lastPostion;

    private LocalValidate mLocalValidate = null;
    private String mSerialNoStr = null;
    private String mSerialVeryCodeStr = null;
    private String deviceType = null;

    private String devType="";

    private EasyPopup easyPopup;

    private int page = 1;

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
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageIndex", "1");
                map.put("pageSize", "10");
                map.put("userId", SpUtils.getUserId(getContext()));
                ViseUtil.Post(getContext(), NetUrl.getBindDeviceList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        GetBindDeviceListBean bean = gson.fromJson(s, GetBindDeviceListBean.class);
                        mList.clear();
                        mList.addAll(bean.getData().getRecords());
                        adapter.notifyDataSetChanged();
                        mListGrid.clear();
                        mListGrid.addAll(bean.getData().getRecords());
                        adapterGrid.notifyDataSetChanged();
                        page = 2;
                        lazyDeviceInfo();
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageIndex", page+"");
                map.put("pageSize", "10");
                map.put("userId", SpUtils.getUserId(getContext()));
                ViseUtil.Post(getContext(), NetUrl.getBindDeviceList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        GetBindDeviceListBean bean = gson.fromJson(s, GetBindDeviceListBean.class);
                        mList.addAll(bean.getData().getRecords());
                        adapter.notifyDataSetChanged();
                        mListGrid.addAll(bean.getData().getRecords());
                        adapterGrid.notifyDataSetChanged();
                        page = page+1;
                        lazyDeviceInfo();
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "10");
        map.put("userId", SpUtils.getUserId(getContext()));
        ViseUtil.Post(getContext(), NetUrl.getBindDeviceList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetBindDeviceListBean bean = gson.fromJson(s, GetBindDeviceListBean.class);
                mList = bean.getData().getRecords();
                adapter = new IndexAdapter(mList, new IndexAdapter.ClickListener() {
                    @Override
                    public void onMore(int pos) {
                        showMorePop(pos);
                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                mListGrid = bean.getData().getRecords();
                adapterGrid = new IndexGridAdapter(mListGrid, new IndexGridAdapter.ClickListener() {
                    @Override
                    public void onMore(int pos) {
                        showMorePop(pos);
                    }
                });
                GridLayoutManager manager1 = new GridLayoutManager(getContext(), 2);
                rvGrid.setLayoutManager(manager1);
                rvGrid.setAdapter(adapterGrid);
                page = 2;
                lazyDeviceInfo();
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    private void lazyDeviceInfo(){
        for (int i = 0; i<mList.size(); i++){
            Map<String, String> map1 = new LinkedHashMap<>();
            map1.put("bindDeviceId", mList.get(i).getId()+"");
            map1.put("userId", SpUtils.getUserId(getContext()));
            int finalI = i;
            ViseUtil.Post(getContext(), NetUrl.getBindDeviceStatusInfo, map1, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson1 = new Gson();
                    GetBindDeviceStatusInfoBean bean1 = gson1.fromJson(s, GetBindDeviceStatusInfoBean.class);
                    mList.get(finalI).setDeviceStatus(bean1.getData().getDeviceStatus());
                    mList.get(finalI).setCloudStorageStatus(bean1.getData().getCloudStorageStatus());
                    mList.get(finalI).setNativeStorageStatus(bean1.getData().getNativeStorageStatus());
                    mList.get(finalI).setIsHaveNewMessage(bean1.getData().getIsHaveNewMessage());
                    adapter.notifyDataSetChanged();
                    mListGrid.get(finalI).setDeviceStatus(bean1.getData().getDeviceStatus());
                    adapterGrid.notifyDataSetChanged();
                }

                @Override
                public void onElse(String s) {
                    Logger.e("getBindDeviceStatusInfo", s);
                }
            });
        }
    }

    /**
     * 显示更多操作pop
     */
    private void showMorePop(int pos) {

//        DialogMsgDelete dialogMsgDelete = new DialogMsgDelete(getContext(), "确定解绑设备吗？", R.mipmap.lajitong, new DialogMsgDelete.ClickListener() {
//            @Override
//            public void onSure() {
//                Map<String, String> map = new LinkedHashMap<>();
//                map.put("bindDeviceId", mList.get(pos).getId() + "");
//                ViseUtil.Post(getContext(), NetUrl.unBindDevice, map, new ViseUtil.ViseListener() {
//                    @Override
//                    public void onReturn(String s) {
//                        ToastUtil.showShort(getContext(), "设备解绑成功");
//                        initData();
//                    }
//
//                    @Override
//                    public void onElse(String s) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//        });
//        dialogMsgDelete.show();

        easyPopup = EasyPopup.create(getContext())
                .setContentView(R.layout.popupwindow_index_more)
                .setWidth(LinearLayout.LayoutParams.WRAP_CONTENT)
                .setHeight(LinearLayout.LayoutParams.WRAP_CONTENT)
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.5f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                .apply();
        if(isNavigationBarShowing(getContext())){
            easyPopup.showAtAnchorView(getActivity().getWindow().getDecorView(), YGravity.BELOW, XGravity.CENTER, 0, 0);
        }else{
            easyPopup.showAtAnchorView(getActivity().getWindow().getDecorView(), YGravity.ALIGN_BOTTOM, XGravity.CENTER, 0, 0);
        }

        TextView tvCancel = easyPopup.findViewById(R.id.tv_cancel);
        TextView tvJiebang = easyPopup.findViewById(R.id.tv_jiebang);
        TextView tvSet = easyPopup.findViewById(R.id.tv_set);
        TextView tvMsg = easyPopup.findViewById(R.id.tv_msg);
        TextView tvDeviceName = easyPopup.findViewById(R.id.tv_device_name);

        tvDeviceName.setText("设备名称:"+mList.get(pos).getDeviceName());

        tvMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyPopup.dismiss();
//                MainActivity activity = (MainActivity) getActivity();
//                activity.selectFragment(1);
                int brandId = mList.get(pos).getBrandId();
                if(brandId == 1){
                    Intent intent = new Intent();
                    intent.setClass(getContext(), MsgLcShebeiListActivity.class);
//                    intent.putExtra("bean", data.get(i));
                    intent.putExtra("name", mList.get(pos).getDeviceName());
                    intent.putExtra("id", mList.get(pos).getId()+"");
                    startActivity(intent);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), MsgYsShebeiListActivity.class);
//                    intent.putExtra("bean", data.get(i));
                    intent.putExtra("name", mList.get(pos).getDeviceName());
                    intent.putExtra("id", mList.get(pos).getId()+"");
                    startActivity(intent);
                }
            }
        });

        tvSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), IndexSetActivity.class);
                intent.putExtra("id", mList.get(pos).getId()+"");
                startActivity(intent);
                easyPopup.dismiss();
            }
        });

        tvJiebang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyPopup.dismiss();
                if(mList.get(pos).getDeviceStatus() == 0){
                    Toast.makeText(getContext(), "设备不在线，无法解绑", Toast.LENGTH_SHORT).show();
                }else {
                    DialogMsgDelete dialogMsgDelete = new DialogMsgDelete(getContext(), "确定解绑设备吗？", R.mipmap.lajitong, new DialogMsgDelete.ClickListener() {
                        @Override
                        public void onSure() {
                            Map<String, String> map = new LinkedHashMap<>();
                            map.put("bindDeviceId", mList.get(pos).getId() + "");
                            ViseUtil.Post(getContext(), NetUrl.unBindDevice, map, new ViseUtil.ViseListener() {
                                @Override
                                public void onReturn(String s) {
                                    ToastUtil.showShort(getContext(), "设备解绑成功");
                                    initData();
                                }

                                @Override
                                public void onElse(String s) {

                                }
                            });
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                    dialogMsgDelete.show();
                }
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyPopup.dismiss();
            }
        });

    }

    private static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }

    public static boolean isNavigationBarShowing(Context context) {
        //判断手机底部是否支持导航栏显示
        boolean haveNavigationBar = checkDeviceHasNavigationBar(context);
        if (haveNavigationBar) {
            if (Build.VERSION.SDK_INT >= 17) {
                String brand = Build.BRAND;
                String mDeviceInfo;
                if (brand.equalsIgnoreCase("HUAWEI")) {
                    mDeviceInfo = "navigationbar_is_min";
                } else if (brand.equalsIgnoreCase("XIAOMI")) {
                    mDeviceInfo = "force_fsg_nav_bar";
                } else if (brand.equalsIgnoreCase("VIVO")) {
                    mDeviceInfo = "navigation_gesture_on";
                } else if (brand.equalsIgnoreCase("OPPO")) {
                    mDeviceInfo = "navigation_gesture_on";
                } else {
                    mDeviceInfo = "navigationbar_is_min";
                }

                if (Settings.Global.getInt(context.getContentResolver(), mDeviceInfo, 0) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @OnClick({R.id.rl_left, R.id.rl_right, R.id.btn_login, R.id.ll_search})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.ll_search:
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_left:
                if(recyclerView.getVisibility() == View.VISIBLE){
                    lastPostion = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    rvGrid.scrollToPosition(lastPostion);
                    recyclerView.setVisibility(View.GONE);
                    rvGrid.setVisibility(View.VISIBLE);
                }else {
                    lastPostion = ((GridLayoutManager)rvGrid.getLayoutManager()).findFirstVisibleItemPosition();
                    recyclerView.scrollToPosition(lastPostion);
                    recyclerView.setVisibility(View.VISIBLE);
                    rvGrid.setVisibility(View.GONE);
                }
//                if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
//                    lastPostion = ((GridLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                }else {
//                    lastPostion = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
//                }
//                recyclerView.setAdapter(adapter);
//                recyclerView.scrollToPosition(lastPostion);
                break;
            case R.id.rl_right:
                PermissionManager.instance().request(getActivity(), new OnPermissionCallback() {
                    @Override
                    public void onRequestAllow(String permissionName) {
                        startActivityForResult(new Intent(getContext(), CaptureActivity.class), 1001);
                    }

                    @Override
                    public void onRequestRefuse(String permissionName) {

                    }

                    @Override
                    public void onRequestNoAsk(String permissionName) {
                        startActivityForResult(new Intent(getContext(), CaptureActivity.class), 1001);
                    }
                }, Manifest.permission.CAMERA);
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
