package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MsgPushTimeActivity extends BaseActivity {

    private Context context = MsgPushTimeActivity.this;

    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_push_time);

        StatusBarUtils.setStatusBar(MsgPushTimeActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MsgPushTimeActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_save, R.id.rl_start, R.id.rl_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_save:
                onSave();
                break;
            case R.id.rl_start:
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
                        .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .isDialog(false)//是否显示为对话框样式
                        .build();
                pvTime.show();
                break;
            case R.id.rl_end:
                //时间选择器
                TimePickerView pvTime1 = new TimePickerBuilder(context, new OnTimeSelectListener() {
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
                        .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .isDialog(false)//是否显示为对话框样式
                        .build();
                pvTime1.show();
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    private void onSave() {

        String start = tvStart.getText().toString().trim();
        String end = tvEnd.getText().toString().trim();
        String startTime = "2020-01-01 "+start+":00";
        String endTime = "2020-01-01 "+end+":00";
        int i = StringUtils.getTimeCompareSize(startTime, endTime);
        if(StringUtils.isEmpty(start)||StringUtils.isEmpty(end)){
            ToastUtil.showShort(context, "起始时间或结束时间不能为空");
        }else if(i == 3){
            PushServiceFactory.getCloudPushService().setDoNotDisturb(Integer.valueOf(start.split(":")[0]), Integer.valueOf(start.split(":")[1]),
                    Integer.valueOf(end.split(":")[0]), Integer.valueOf(end.split(":")[1]), new CommonCallback() {
                        @Override
                        public void onSuccess(String s) {
                            Logger.e("123123", s);
                            SpUtils.setPushTime(context, start+"~"+end);
                            ToastUtil.showShort(context, "设置成功");
                            finish();
                        }

                        @Override
                        public void onFailed(String s, String s1) {

                        }
                    });
        }else {
            ToastUtil.showShort(context, "结束时间不能大于起始时间");
        }

    }

}
