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
import com.jiufang.wsyapp.bean.GetYsDeviceCapabilityBean;
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

public class ZhencebufangYsActivity extends BaseActivity {

    private Context context = ZhencebufangYsActivity.this;

    @BindView(R.id.iv_huamian)
    ImageView ivHuamian;
    @BindView(R.id.tv_huamian)
    TextView tvHuamian;
    @BindView(R.id.iv_yidong)
    ImageView ivYidong;
    @BindView(R.id.tv_yidong)
    TextView tvYidong;
    @BindView(R.id.iv_intell)
    ImageView ivIntell;
    @BindView(R.id.tv_intell)
    TextView tvIntell;
    @BindView(R.id.tv_zhence_lingmie)
    TextView tvZhenceLingmie;
    @BindView(R.id.tv_zhence_zhouqi)
    TextView tvZhenceZhouqi;
    @BindView(R.id.tv_baojing)
    TextView tvBaojing;
    @BindView(R.id.tv_time)
    TextView tvTime;

    private String id = "";
    private int mon = 0;//画面变化检测
    private int track = 0;//移动追踪
    private int intell = 0;//智能检测

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhencebufang_ys);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(ZhencebufangYsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(ZhencebufangYsActivity.this);
        initData();

    }

    private void initData() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        map.put("deviceId", id);
        ViseUtil.Post(context, NetUrl.getYsDeviceCapability, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetYsDeviceCapabilityBean bean = gson.fromJson(s, GetYsDeviceCapabilityBean.class);
                mon = bean.getData().getMonitoringStatus();
                if(mon == 0){
                    ivHuamian.setImageResource(R.mipmap.turn_off);
                }else if(mon == 1){
                    ivHuamian.setImageResource(R.mipmap.turn_on);
                }else if(mon == -1){
                    tvHuamian.setText("画面变化检测（该设备不支持）");
                }
                track = bean.getData().getTrackingStatus();
                if(track == 0){
                    ivYidong.setImageResource(R.mipmap.turn_off);
                }else if(track == 1){
                    ivYidong.setImageResource(R.mipmap.turn_on);
                }else if(track == -1){
                    tvYidong.setText("移动追踪（该设备不支持）");
                }
                intell = bean.getData().getIntelligentCheckStatus();
                if(intell == 0){
                    ivIntell.setImageResource(R.mipmap.turn_off);
                }else if(intell == 1){
                    ivIntell.setImageResource(R.mipmap.turn_on);
                }else if(intell == -1){
                    tvIntell.setText("智能检测（该设备不支持）");
                }
                tvZhenceLingmie.setText(bean.getData().getSensitivity());
                String zhouqi = bean.getData().getDetectPeriod();
                zhouqi = zhouqi.replaceAll(",", "/");
                zhouqi = getZhouqi(zhouqi);
                String[] zq = zhouqi.split("/");
                if(zq.length == 7){
                    tvZhenceZhouqi.setText("每天");
                }else {
                    tvZhenceZhouqi.setText(zhouqi);
                }
                String baojing = bean.getData().getAlarmSoundMode();
                if(baojing.equals("0")){
                    tvBaojing.setText("短叫");
                }else if(baojing.equals("1")){
                    tvBaojing.setText("长叫");
                }else if(baojing.equals("2")){
                    tvBaojing.setText("静音");
                }
                String startTime = "2020-01-01 "+bean.getData().getScheduleStartTime()+":00";
                String endTime = "2020-01-01 "+bean.getData().getScheduleEndTime()+":00";
                int i = StringUtils.getTimeCompareSize(startTime, endTime);
                if(i == 3){
                    tvTime.setText("每天"+bean.getData().getScheduleStartTime()+"~"+bean.getData().getScheduleEndTime());
                }else {
                    tvTime.setText("每天"+bean.getData().getScheduleStartTime()+"~次日"+bean.getData().getScheduleEndTime());
                }

            }

            @Override
            public void onElse(String s) {
                Logger.e("123123", s);
            }
        });

    }

    private String getZhouqi(String zq){
        zq = zq.replace("0", "周一");
        zq = zq.replace("1", "周二");
        zq = zq.replace("2", "周三");
        zq = zq.replace("3", "周四");
        zq = zq.replace("4", "周五");
        zq = zq.replace("5", "周六");
        zq = zq.replace("6", "周日");
        return zq;
    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
