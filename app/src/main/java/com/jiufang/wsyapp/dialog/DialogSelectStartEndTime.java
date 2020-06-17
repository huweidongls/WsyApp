package com.jiufang.wsyapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.utils.StringUtils;

import java.util.Date;

/**
 * Created by Administrator on 2020/6/17.
 */

public class DialogSelectStartEndTime extends Dialog {

    private Context context;
    private TextView tvStart;
    private TextView tvEnd;
    private TextView tvSure;

    public DialogSelectStartEndTime(@NonNull Context context) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_select_start_end_time, null);
        setContentView(view);

        tvStart = view.findViewById(R.id.tv_start);
        tvEnd = view.findViewById(R.id.tv_end);
        tvSure = view.findViewById(R.id.tv_sure);

        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        .setTitleText("期望送达时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(0xFF23C1C7)//确定按钮文字颜色
                        .setCancelColor(0xFF23C1C7)//取消按钮文字颜色
                        .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                        .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                        .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .isDialog(false)//是否显示为对话框样式
                        .build();
                pvTime.show();
            }
        });
        tvEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
