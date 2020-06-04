package com.jiufang.wsyapp.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.UtilsDevicePic;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceTongdianActivity extends BaseActivity {

    private Context context = AddDeviceTongdianActivity.this;

    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    @BindView(R.id.iv_title)
    ImageView ivTitle;

    private String type = "";//1乐橙 2萤石
    private String xlh = "";
    private String anquan = "";
    private String xinghao = "";//设备型号

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_tongdian);

        type = getIntent().getStringExtra("type");
        xlh = getIntent().getStringExtra("xlh");
        anquan = getIntent().getStringExtra("anquan");
        xinghao = getIntent().getStringExtra("xinghao");
        StatusBarUtils.setStatusBar(AddDeviceTongdianActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceTongdianActivity.this);
        initData();

    }

    private void initData() {

        ivTitle.setImageResource(UtilsDevicePic.getDevicePic(xinghao));
        if(type.equals("1")){
            tv.setText("请将设备连通电源，耐心等待1分钟，直到指示灯变绿色");
            tvBottom.setText("指示灯已经变绿色");
        }else {
            tv.setText("请将设备插上电源，耐心等待1分钟，直到指示灯变蓝色");
            tvBottom.setText("指示灯已经变蓝色");
        }

    }

    @OnClick({R.id.rl_back, R.id.ll_select, R.id.btn_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_select:
                ivSelect.setImageResource(R.mipmap.gouxuan);
                btnNext.setBackgroundResource(R.drawable.bg_ffa16f_3dp);
                btnNext.setFocusable(true);
                btnNext.setEnabled(true);
                break;
            case R.id.btn_next:
                PermissionManager.instance().request(AddDeviceTongdianActivity.this, new OnPermissionCallback() {
                    @Override
                    public void onRequestAllow(String permissionName) {
                        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("snCode", xlh);
                        map.put("userId", SpUtils.getUserId(context));
                        ViseUtil.Post(context, NetUrl.checkDeviceOnlineStatus, map, dialog, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                try {
                                    Logger.e("123123", s);
                                    Intent intent = new Intent();
                                    JSONObject jsonObject = new JSONObject(s);
                                    boolean b = jsonObject.optBoolean("data");
                                    if(b){
                                        //设备有网
                                        intent.setClass(context, AddDeviceSureActivity.class);
                                        intent.putExtra("type", type);
                                        intent.putExtra("xlh", xlh);
                                        intent.putExtra("anquan", anquan);
                                        intent.putExtra("xinghao", xinghao);
                                        startActivity(intent);
                                    }else {
                                        //设备没网
                                        intent.setClass(context, AddDeviceWifiActivity.class);
                                        intent.putExtra("type", type);
                                        intent.putExtra("xlh", xlh);
                                        intent.putExtra("anquan", anquan);
                                        intent.putExtra("xinghao", xinghao);
                                        startActivity(intent);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onElse(String s) {
                                Logger.e("123123", "fail"+s);
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    if(jsonObject.optInt("code") == 1203||jsonObject.optInt("code") == 1103){
                                        Intent intent = new Intent();
                                        intent.setClass(context, AddDeviceWifiActivity.class);
                                        intent.putExtra("type", type);
                                        intent.putExtra("xlh", xlh);
                                        intent.putExtra("anquan", anquan);
                                        intent.putExtra("xinghao", xinghao);
                                        startActivity(intent);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    public void onRequestRefuse(String permissionName) {
                        ToastUtil.showShort(context, "未开启定位权限");
                    }

                    @Override
                    public void onRequestNoAsk(String permissionName) {
                        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("snCode", xlh);
                        map.put("userId", SpUtils.getUserId(context));
                        ViseUtil.Post(context, NetUrl.checkDeviceOnlineStatus, map, dialog, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                try {
                                    Logger.e("123123", s);
                                    Intent intent = new Intent();
                                    JSONObject jsonObject = new JSONObject(s);
                                    boolean b = jsonObject.optBoolean("data");
                                    if(b){
                                        //设备有网
                                        intent.setClass(context, AddDeviceSureActivity.class);
                                        intent.putExtra("type", type);
                                        intent.putExtra("xlh", xlh);
                                        intent.putExtra("anquan", anquan);
                                        intent.putExtra("xinghao", xinghao);
                                        startActivity(intent);
                                    }else {
                                        //设备没网
                                        intent.setClass(context, AddDeviceWifiActivity.class);
                                        intent.putExtra("type", type);
                                        intent.putExtra("xlh", xlh);
                                        intent.putExtra("anquan", anquan);
                                        intent.putExtra("xinghao", xinghao);
                                        startActivity(intent);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onElse(String s) {
                                Logger.e("123123", "fail"+s);
                            }
                        });
                    }
                }, Manifest.permission.ACCESS_FINE_LOCATION);
                break;
        }
    }

}
