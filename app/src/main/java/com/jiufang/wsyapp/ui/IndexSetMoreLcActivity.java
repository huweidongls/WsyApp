package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetLcDeviceMoreCapabilityBean;
import com.jiufang.wsyapp.dialog.DialogReset;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.DensityTool;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;
import com.jiufang.wsyapp.widget.DatePickerView;
import com.zyyoona7.popup.EasyPopup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexSetMoreLcActivity extends BaseActivity {

    private Context context = IndexSetMoreLcActivity.this;

    @BindView(R.id.tv_hongwaideng)
    TextView tvHongwaideng;
    @BindView(R.id.tv_hwd)
    TextView tvHwd;
    @BindView(R.id.tv_zhishideng)
    TextView tvZhishideng;
    @BindView(R.id.iv_zhishideng)
    ImageView ivZhishideng;
    @BindView(R.id.tv_tishiyin)
    TextView tvTishiyin;
    @BindView(R.id.iv_tishiyin)
    ImageView ivTishiyin;
    @BindView(R.id.tv_fanzhuan)
    TextView tvFanzhuan;
    @BindView(R.id.iv_fanzhuan)
    ImageView ivFanzhuan;

    private String id = "";
    private int infraredLightStatus = 0;
    private int breathingLampStatus = 0;
    private int soundsStatus = 0;
    private int reverseStatus = 0;

    private EasyPopup easyPopup;
    private Dialog dialog;

    private int mPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_set_more_lc);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(IndexSetMoreLcActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(IndexSetMoreLcActivity.this);
        initData();

    }

    private void initData() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getLcDeviceMoreCapability, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetLcDeviceMoreCapabilityBean bean = gson.fromJson(s, GetLcDeviceMoreCapabilityBean.class);
                infraredLightStatus = bean.getData().getInfraredLightStatus();
                if(infraredLightStatus == 0){
                    tvHwd.setText("禁用");
                }else if(infraredLightStatus == 1){
                    tvHwd.setText("自动");
                }else if(infraredLightStatus == -1){
                    tvHongwaideng.setText("红外灯设置（设备不支持）");
                    tvHwd.setText(null);
                }
                breathingLampStatus = bean.getData().getBreathingLampStatus();
                if(breathingLampStatus == 0){
                    ivZhishideng.setImageResource(R.mipmap.turn_off);
                }else if(breathingLampStatus == 1){
                    ivZhishideng.setImageResource(R.mipmap.turn_on);
                }else if(breathingLampStatus == -1){
                    tvZhishideng.setText("设备指示灯（设备不支持）");
                }
                soundsStatus = bean.getData().getSoundsStatus();
                if(soundsStatus == 0){
                    ivTishiyin.setImageResource(R.mipmap.turn_off);
                }else if(soundsStatus == 1){
                    ivTishiyin.setImageResource(R.mipmap.turn_on);
                }else if(soundsStatus == -1){
                    tvTishiyin.setText("设备提示音（设备不支持）");
                }
                reverseStatus = bean.getData().getReverseStatus();
                if(reverseStatus == 0){
                    ivFanzhuan.setImageResource(R.mipmap.turn_off);
                }else if(reverseStatus == 1){
                    ivFanzhuan.setImageResource(R.mipmap.turn_on);
                }else if(reverseStatus == -1){
                    tvFanzhuan.setText("摄像机画面翻转（设备不支持）");
                }
            }

            @Override
            public void onElse(String s) {
                Logger.e("123123", s);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_reset, R.id.rl_hongwaideng_set, R.id.iv_tishiyin, R.id.iv_zhishideng, R.id.iv_fanzhuan})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_fanzhuan:
                if(reverseStatus == 0){
                    setFanzhuan(1);
                }else if(reverseStatus == 1){
                    setFanzhuan(0);
                }
                break;
            case R.id.iv_zhishideng:
                if(breathingLampStatus == 0){
                    setZhishideng(1);
                }else if(breathingLampStatus == 1){
                    setZhishideng(0);
                }
                break;
            case R.id.iv_tishiyin:
                if(soundsStatus == 0){
                    setTishiyin(1);
                }else if(soundsStatus == 1){
                    setTishiyin(0);
                }
                break;
            case R.id.rl_hongwaideng_set:
                if(infraredLightStatus == 0){
                    showHongwaidengSetPop(1);
                }else if(infraredLightStatus == 1){
                    showHongwaidengSetPop(0);
                }
                break;
            case R.id.rl_reset:
                DialogReset dialogReset = new DialogReset(context, new DialogReset.ClickListener() {
                    @Override
                    public void onSure() {
                        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("deviceId", id);
                        map.put("userId", SpUtils.getUserId(context));
                        ViseUtil.Post(context, NetUrl.restartLcDevice, map, dialog, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                ToastUtil.showShort(context, "操作成功");
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }
                });
                dialogReset.show();
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    /**
     * 设置设备画面翻转
     * @param i
     */
    private void setFanzhuan(int i) {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("status", i+"");
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setLcReverseStatus, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                if(i == 0){
                    reverseStatus = 0;
                    ivFanzhuan.setImageResource(R.mipmap.turn_off);
                }else if(i == 1){
                    reverseStatus = 1;
                    ivFanzhuan.setImageResource(R.mipmap.turn_on);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    /**
     * 设置设备指示灯
     * @param i
     */
    private void setZhishideng(int i) {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("status", i+"");
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setLcBreathingLampStatus, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                if(i == 0){
                    breathingLampStatus = 0;
                    ivZhishideng.setImageResource(R.mipmap.turn_off);
                }else if(i == 1){
                    breathingLampStatus = 1;
                    ivZhishideng.setImageResource(R.mipmap.turn_on);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    /**
     * 设置设备提示音
     * @param i
     */
    private void setTishiyin(int i) {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("userId", SpUtils.getUserId(context));
        map.put("enableType", "PlaySound");
        if(i == 0){
            map.put("enable", "false");
        }else if(i == 1){
            map.put("enable", "true");
        }
        ViseUtil.Post(context, NetUrl.setLcDeviceCapabilityStatus, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                if(i == 0){
                    soundsStatus = 0;
                    ivTishiyin.setImageResource(R.mipmap.turn_off);
                }else if(i == 1){
                    soundsStatus = 1;
                    ivTishiyin.setImageResource(R.mipmap.turn_on);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    private void showHongwaidengSetPop(int i) {

        ArrayList<String> list = new ArrayList<>();
        list.add("自动");
        list.add("禁用");

        easyPopup = EasyPopup.create(context)
                .setContentView(context, R.layout.popupwindow_hongwaideng_set)
                .setWidth(RelativeLayout.LayoutParams.MATCH_PARENT)
                .setHeight(DensityTool.dp2px(context, 227))
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.5f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                .apply();
        easyPopup.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        TextView tvCancel = easyPopup.findViewById(R.id.tv_cancel);
        TextView tvSure = easyPopup.findViewById(R.id.tv_sure);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyPopup.dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyPopup.dismiss();
                dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                Map<String, String> map = new LinkedHashMap<>();
                map.put("deviceId", id);
                map.put("userId", SpUtils.getUserId(context));
                map.put("enableType", "infraredLight");
                if(mPos == 0){
                    map.put("enable", "true");
                }else if(mPos == 1){
                    map.put("enable", "false");
                }
                ViseUtil.Post(context, NetUrl.setLcDeviceCapabilityStatus, map, dialog, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        if(mPos == 0){
                            infraredLightStatus = 1;
                            tvHwd.setText("自动");
                        }else if(mPos == 1){
                            infraredLightStatus = 0;
                            tvHwd.setText("禁用");
                        }
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
            }
        });

        DatePickerView datePickerView = easyPopup.findViewById(R.id.select);
        datePickerView.setIsLoop(false);
        datePickerView.setData(list);
        datePickerView.setSelected(i);
        datePickerView.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(int pos) {
                mPos = pos;
            }
        });

    }

}
