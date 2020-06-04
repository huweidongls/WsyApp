package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.BindDeviceBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.UtilsDevicePic;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceSureActivity extends BaseActivity {

    private Context context = AddDeviceSureActivity.this;

    @BindView(R.id.iv_gouxuan)
    ImageView ivGouxuan;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    private String type = "";//1乐橙 2萤石
    private String xlh = "";
    private String anquan = "";
    private String xinghao = "";//设备型号

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_sure);

        type = getIntent().getStringExtra("type");
        xlh = getIntent().getStringExtra("xlh");
        anquan = getIntent().getStringExtra("anquan");
        xinghao = getIntent().getStringExtra("xinghao");
        StatusBarUtils.setStatusBar(AddDeviceSureActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceSureActivity.this);
        initData();

    }

    private void initData() {

        ivTitle.setImageResource(UtilsDevicePic.getDevicePic(xinghao));
        if(type.equals("1")){
            tv1.setText("请确认设备绿灯常亮");
            tv2.setText("未见设备绿灯常亮？联系客服");
            tv3.setText("已确认设备绿灯常亮");
        }else {
            tv1.setText("请确认设备蓝灯慢闪");
            tv2.setText("未见设备蓝灯慢闪？联系客服");
            tv3.setText("已确认设备蓝灯慢闪");
        }

    }

    @OnClick({R.id.rl_back, R.id.ll_gouxuan, R.id.btn_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_gouxuan:
                ivGouxuan.setImageResource(R.mipmap.gouxuan);
                btnNext.setBackgroundResource(R.drawable.bg_ffa16f_3dp);
                btnNext.setFocusable(true);
                btnNext.setEnabled(true);
                break;
            case R.id.btn_next:
                bind();
                break;
        }
    }

    private void bind() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceSn", xlh);
        map.put("userId", SpUtils.getUserId(context));
        if(!StringUtils.isEmpty(anquan)){
            map.put("deviceSecurityCode", anquan);
        }
        ViseUtil.Post(context, NetUrl.bindDevice, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                BindDeviceBean bean = gson.fromJson(s, BindDeviceBean.class);
                Intent intent = new Intent();
                intent.setClass(context, AddDeviceSuccessActivity.class);
                intent.putExtra("id", bean.getData().getId()+"");
                intent.putExtra("xinghao", xinghao);
                startActivity(intent);
            }

            @Override
            public void onElse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Intent intent = new Intent();
                    intent.setClass(context, AddDeviceAnquanActivity.class);
                    intent.putExtra("type", type);
                    intent.putExtra("xlh", xlh);
                    intent.putExtra("xinghao", xinghao);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
