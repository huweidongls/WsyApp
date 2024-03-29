package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.BindDeviceBean;
import com.jiufang.wsyapp.dialog.DialogBohao;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
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

    @OnClick({R.id.rl_back, R.id.ll_gouxuan, R.id.btn_next, R.id.rl_right})
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
                if(type.equals("1")){
                    bind();
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("snCode", xlh);
                    map.put("userId", SpUtils.getUserId(context));
                    ViseUtil.Post(context, NetUrl.checkDeviceOnlineStatus, map, dialog, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                boolean b = jsonObject.optBoolean("data");
                                if(b){
                                    //设备有网
                                    bind();
                                }else {
                                    //设备没网
                                    ToastUtil.showShort(context, "配网中，请等待...");
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
                break;
            case R.id.rl_right:
                DialogBohao dialogBohao = new DialogBohao(context, new DialogBohao.ClickListener() {
                    @Override
                    public void onSure() {
                        ViseUtil.Post(context, NetUrl.getServiceNumber, null, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                Logger.e("123123", s);
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    String phone = jsonObject.optString("data");
                                    if(StringUtils.isEmpty(phone)){
                                        ToastUtil.showShort(context, "客服电话维护中，暂时无法拨号");
                                    }else {
                                        Intent intent1 = new Intent(Intent.ACTION_DIAL);
                                        Uri data = Uri.parse("tel:" + phone);
                                        intent1.setData(data);
                                        startActivity(intent1);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }
                });
                dialogBohao.show();
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
                    if(jsonObject.optInt("code") == 1007){
                        Intent intent = new Intent();
                        intent.setClass(context, AddDeviceAnquanActivity.class);
                        intent.putExtra("type", type);
                        intent.putExtra("xlh", xlh);
                        intent.putExtra("xinghao", xinghao);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
