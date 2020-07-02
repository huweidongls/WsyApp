package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhenceTimeActivity extends BaseActivity {

    private Context context = ZhenceTimeActivity.this;

    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;

    private String type = "";
    private String id = "";
    private String zhouqi = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhence_time);

        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        zhouqi = getIntent().getStringExtra("zhouqi");
        StatusBarUtils.setStatusBar(ZhenceTimeActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(ZhenceTimeActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.rl_save, R.id.rl_start, R.id.rl_end})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_save:
                if(type.equals("1")){
                    setLc();
                }else if(type.equals("2")){
                    setYs();
                }
                break;
            case R.id.rl_start:
                if(type.equals("1")){
                    //时间选择器
                    TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            tvStart.setText(StringUtils.dateToString(date));
                        }
                    }).setType(new boolean[]{false, false, false, true, true, true})// 默认全部显示
                            .setCancelText("取消")//取消按钮文字
                            .setSubmitText("确认")//确认按钮文字
                            .setTitleSize(20)//标题文字大小
                            .setTitleText("选择起始时间")//标题文字
                            .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                            .isCyclic(true)//是否循环滚动
                            .setTitleColor(Color.BLACK)//标题文字颜色
                            .setSubmitColor(0xFFFFA16F)//确定按钮文字颜色
                            .setCancelColor(0xFFFFA16F)//取消按钮文字颜色
                            .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                            .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                            .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                            .isDialog(false)//是否显示为对话框样式
                            .build();
                    pvTime.show();
                }else if(type.equals("2")){
                    //时间选择器
                    TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            tvStart.setText(StringUtils.dateToString1(date));
                        }
                    }).setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                            .setCancelText("取消")//取消按钮文字
                            .setSubmitText("确认")//确认按钮文字
                            .setTitleSize(20)//标题文字大小
                            .setTitleText("选择起始时间")//标题文字
                            .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                            .isCyclic(true)//是否循环滚动
                            .setTitleColor(Color.BLACK)//标题文字颜色
                            .setSubmitColor(0xFFFFA16F)//确定按钮文字颜色
                            .setCancelColor(0xFFFFA16F)//取消按钮文字颜色
                            .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                            .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                            .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                            .isDialog(false)//是否显示为对话框样式
                            .build();
                    pvTime.show();
                }
                break;
            case R.id.rl_end:
                if(type.equals("1")){
                    //时间选择器
                    TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            tvEnd.setText(StringUtils.dateToString(date));
                        }
                    }).setType(new boolean[]{false, false, false, true, true, true})// 默认全部显示
                            .setCancelText("取消")//取消按钮文字
                            .setSubmitText("确认")//确认按钮文字
                            .setTitleSize(20)//标题文字大小
                            .setTitleText("选择结束时间")//标题文字
                            .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                            .isCyclic(true)//是否循环滚动
                            .setTitleColor(Color.BLACK)//标题文字颜色
                            .setSubmitColor(0xFFFFA16F)//确定按钮文字颜色
                            .setCancelColor(0xFFFFA16F)//取消按钮文字颜色
                            .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                            .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                            .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                            .isDialog(false)//是否显示为对话框样式
                            .build();
                    pvTime.show();
                }else if(type.equals("2")){
                    //时间选择器
                    TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            tvEnd.setText(StringUtils.dateToString1(date));
                        }
                    }).setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                            .setCancelText("取消")//取消按钮文字
                            .setSubmitText("确认")//确认按钮文字
                            .setTitleSize(20)//标题文字大小
                            .setTitleText("选择结束时间")//标题文字
                            .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                            .isCyclic(true)//是否循环滚动
                            .setTitleColor(Color.BLACK)//标题文字颜色
                            .setSubmitColor(0xFFFFA16F)//确定按钮文字颜色
                            .setCancelColor(0xFFFFA16F)//取消按钮文字颜色
                            .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                            .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                            .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                            .isDialog(false)//是否显示为对话框样式
                            .build();
                    pvTime.show();
                }
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    /**
     * 设置萤石
     */
    private void setYs() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        String startTime = tvStart.getText().toString().trim();
        String endTime = tvEnd.getText().toString().trim();

        String mStartTime = "2020-01-01 "+startTime+":00";
        String mEndTime = "2020-01-01 "+endTime+":00";
        int i = StringUtils.getTimeCompareSize(mStartTime, mEndTime);
        if(i == 3){

        }else {
            endTime = "n"+endTime;
        }
        if(!StringUtils.isEmpty(zhouqi)){
            zhouqi = zhouqi.replaceAll("/", ",");
            zhouqi = getYsZhouqiStr(zhouqi);
        }
        Logger.e("123123", startTime.trim()+"--"+endTime.trim()+"--"+zhouqi);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("period", zhouqi);
        map.put("startTime", startTime.trim());
        map.put("stopTime", endTime.trim());
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setYsSchedule, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "设置成功");
                finish();
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    /**
     * 设置乐橙
     */
    private void setLc() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        String startTime = tvStart.getText().toString().trim();
        String endTime = tvEnd.getText().toString().trim();
        if(!StringUtils.isEmpty(zhouqi)){
            zhouqi = zhouqi.replaceAll("/", ",");
            zhouqi = getLcZhouqiStr(zhouqi);
        }
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("period", zhouqi);
        map.put("beginTime", startTime.trim());
        map.put("endTime", endTime.trim());
        map.put("userId", SpUtils.getUserId(context));
        Logger.e("123123", startTime.trim()+"--"+endTime.trim()+"--"+zhouqi);
        ViseUtil.Post(context, NetUrl.setLcSchedule, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "设置成功");
                finish();
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    private String getYsZhouqiStr(String str){
        str = str.replace("周一", "0");
        str = str.replace("周二", "1");
        str = str.replace("周三", "2");
        str = str.replace("周四", "3");
        str = str.replace("周五", "4");
        str = str.replace("周六", "5");
        str = str.replace("周日", "6");
        return str;
    }

    private String getLcZhouqiStr(String str){
        str = str.replace("周一", "Monday");
        str = str.replace("周二", "Tuesday");
        str = str.replace("周三", "Wednesday");
        str = str.replace("周四", "Thursday");
        str = str.replace("周五", "Friday");
        str = str.replace("周六", "Saturday");
        str = str.replace("周日", "Sunday");
        return str;
    }

}
