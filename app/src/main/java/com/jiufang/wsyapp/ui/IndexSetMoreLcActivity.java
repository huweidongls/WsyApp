package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.dialog.DialogReset;
import com.jiufang.wsyapp.utils.DensityTool;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.widget.DatePickerView;
import com.zyyoona7.popup.EasyPopup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexSetMoreLcActivity extends BaseActivity {

    private Context context = IndexSetMoreLcActivity.this;

    private EasyPopup easyPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_set_more_lc);

        StatusBarUtils.setStatusBar(IndexSetMoreLcActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(IndexSetMoreLcActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_reset, R.id.rl_hongwaideng_set})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_hongwaideng_set:
                showHongwaidengSetPop();
                break;
            case R.id.rl_reset:
                DialogReset dialogReset = new DialogReset(context, new DialogReset.ClickListener() {
                    @Override
                    public void onSure() {
                        ToastUtil.showShort(context, "重启");
                    }
                });
                dialogReset.show();
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    private void showHongwaidengSetPop() {

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

        DatePickerView datePickerView = easyPopup.findViewById(R.id.select);
        datePickerView.setIsLoop(false);
        datePickerView.setData(list);
        datePickerView.setOnSelectListener(new DatePickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {

            }
        });

    }

}
