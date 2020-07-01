package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetLcDeviceCapabilityBean;
import com.jiufang.wsyapp.dialog.DialogLcFenbei;
import com.jiufang.wsyapp.dialog.DialogLcLingmindu;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhencebufangLcActivity extends BaseActivity {

    private Context context = ZhencebufangLcActivity.this;

    @BindView(R.id.tv_huamian)
    TextView tvHuamian;
    @BindView(R.id.iv_huamian)
    ImageView ivHuamian;
    @BindView(R.id.tv_renxing)
    TextView tvRenxing;
    @BindView(R.id.iv_renxing)
    ImageView ivRenxing;
    @BindView(R.id.tv_zhuizong)
    TextView tvZhuizong;
    @BindView(R.id.iv_zhuizong)
    ImageView ivZhuizong;
    @BindView(R.id.tv_lingmindu)
    TextView tvLingmindu;
    @BindView(R.id.tv_zhence_zhouqi)
    TextView tvZhenceZhouqi;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_yichang)
    TextView tvYichang;
    @BindView(R.id.iv_yichang)
    ImageView ivYichang;
    @BindView(R.id.tv_fenbei)
    TextView tvFenbei;

    private String id = "";
    private int mon = 0;
    private int renxing = 0;
    private int isHave = 0;
    private int zhuizong = 0;
    private int yichangyin = 0;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhencebufang);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(ZhencebufangLcActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(ZhencebufangLcActivity.this);
        initData();

    }

    private void initData() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        map.put("deviceId", id);
        ViseUtil.Post(context, NetUrl.getLcDeviceCapability, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetLcDeviceCapabilityBean bean = gson.fromJson(s, GetLcDeviceCapabilityBean.class);
                mon = bean.getData().getMonitoringStatus();
                if(mon == 0){
                    ivHuamian.setImageResource(R.mipmap.turn_off);
                }else if(mon == 1){
                    ivHuamian.setImageResource(R.mipmap.turn_on);
                }else if(mon == -1){
                    tvHuamian.setText("画面变化检测（该设备不支持）");
                }
                renxing = bean.getData().getFaceDetect();
                if(renxing == 0){
                    ivRenxing.setImageResource(R.mipmap.turn_off);
                }else if(renxing == 1){
                    ivRenxing.setImageResource(R.mipmap.turn_on);
                }else if(renxing == -1){
                    tvRenxing.setText("人形检测（该设备不支持）");
                }
                isHave = bean.getData().getIsHaveSmartTrack();
                zhuizong = bean.getData().getSmartTrackStatus();
                if(isHave == 0){
                    zhuizong = -1;
                }
                if(zhuizong == 0){
                    ivZhuizong.setImageResource(R.mipmap.turn_off);
                }else if(zhuizong == 1){
                    ivZhuizong.setImageResource(R.mipmap.turn_on);
                }else if(zhuizong == -1){
                    tvZhuizong.setText("智能追踪（该设备不支持）");
                }
                int lingmin = bean.getData().getSensitivity();
                if(lingmin == -1){
                    tvLingmindu.setText("数据异常");
                }else {
                    tvLingmindu.setText(lingmin+"");
                }
                String zhouqi = bean.getData().getDetectPeriod();
                zhouqi = zhouqi.replaceAll(",", "/");
                zhouqi = getZhouqi(zhouqi);
                String[] zq = zhouqi.split("/");
                if(zq.length == 7){
                    tvZhenceZhouqi.setText("每天");
                }else {
                    tvZhenceZhouqi.setText(zhouqi);
                }
                String startTime = "2020-01-01 "+bean.getData().getScheduleStartTime();
                String endTime = "2020-01-01 "+bean.getData().getScheduleEndTime();
                int i = StringUtils.getTimeCompareSize(startTime, endTime);
                if(i == 3){
                    tvTime.setText("每天"+bean.getData().getScheduleStartTime()+"~"+bean.getData().getScheduleEndTime());
                }else {
                    tvTime.setText("每天"+bean.getData().getScheduleStartTime()+"~次日"+bean.getData().getScheduleEndTime());
                }
                yichangyin = bean.getData().getAlarmSoundStatus();
                if(yichangyin == 0){
                    ivYichang.setImageResource(R.mipmap.turn_off);
                }else if(yichangyin == 1){
                    ivYichang.setImageResource(R.mipmap.turn_on);
                }else if(yichangyin == -1){
                    tvYichang.setText("异常音警报（该设备不支持）");
                }
                tvFenbei.setText(bean.getData().getAlarmSound()+"分贝");
            }

            @Override
            public void onElse(String s) {
                Logger.e("123123", s);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.iv_huamian, R.id.iv_zhuizong, R.id.rl_lingmindu, R.id.iv_yichang, R.id.rl_fenbei})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_fenbei:
                setFenbei();
                break;
            case R.id.iv_yichang:
                if(yichangyin == 0){
                    setYichangyin(1);
                }else if(yichangyin == 1){
                    setYichangyin(0);
                }
                break;
            case R.id.rl_lingmindu:
                setLingmindu();
                break;
            case R.id.iv_zhuizong:
                if(zhuizong == 0){
                    setZhuizong(1);
                }else if(zhuizong == 1){
                    setZhuizong(0);
                }
                break;
            case R.id.iv_huamian:
                if(mon == 0){
                    setHuamian(1);
                }else if(mon == 1){
                    setHuamian(0);
                }
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    /**
     * 设置声音分贝
     */
    private void setFenbei() {

        DialogLcFenbei dialogLcFenbei = new DialogLcFenbei(context, new DialogLcFenbei.ClickListener() {
            @Override
            public void onClick(String value) {
                dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                Map<String, String> map = new LinkedHashMap<>();
                map.put("deviceId", id);
                map.put("userId", SpUtils.getUserId(context));
                map.put("value", value);
                ViseUtil.Post(context, NetUrl.setLcSoundVolumeSize, map, dialog, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        tvFenbei.setText(value+"分贝");
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
            }
        });
        dialogLcFenbei.show();

    }

    /**
     * 设置异常音警报
     * @param i
     */
    private void setYichangyin(int i) {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("status", i+"");
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setLcAlarmSound, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                if(i == 0){
                    yichangyin = 0;
                    ivYichang.setImageResource(R.mipmap.turn_off);
                }else if(i == 1){
                    yichangyin = 1;
                    ivYichang.setImageResource(R.mipmap.turn_on);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    /**
     * 设置侦测灵敏度
     */
    private void setLingmindu() {

        DialogLcLingmindu dialogLcLingmindu = new DialogLcLingmindu(context, new DialogLcLingmindu.ClickListener() {
            @Override
            public void onClick(String value) {
                dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                Map<String, String> map = new LinkedHashMap<>();
                map.put("deviceId", id);
                map.put("userId", SpUtils.getUserId(context));
                map.put("value", value);
                ViseUtil.Post(context, NetUrl.setSensitivity, map, dialog, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        tvLingmindu.setText(value);
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
            }
        });
        dialogLcLingmindu.show();

    }

    /**
     * 设置智能追踪
     * @param i
     */
    private void setZhuizong(int i) {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("status", i+"");
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setLcSmartTracking, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                if(i == 0){
                    zhuizong = 0;
                    ivZhuizong.setImageResource(R.mipmap.turn_off);
                }else if(i == 1){
                    zhuizong = 1;
                    ivZhuizong.setImageResource(R.mipmap.turn_on);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    /**
     * 设置画面变化检测
     * @param i
     */
    private void setHuamian(int i) {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("status", i+"");
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setMonitoringStatus, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                if(i == 0){
                    mon = 0;
                    ivHuamian.setImageResource(R.mipmap.turn_off);
                }else if(i == 1){
                    mon = 1;
                    ivHuamian.setImageResource(R.mipmap.turn_on);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    private String getZhouqi(String zq){
        zq = zq.replace("Monday", "周一");
        zq = zq.replace("Tuesday", "周二");
        zq = zq.replace("Wednesday", "周三");
        zq = zq.replace("Thursday", "周四");
        zq = zq.replace("Friday", "周五");
        zq = zq.replace("Saturday", "周六");
        zq = zq.replace("Sunday", "周日");
        return zq;
    }

}
