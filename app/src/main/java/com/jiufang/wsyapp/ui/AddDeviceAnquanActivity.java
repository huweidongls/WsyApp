package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.BindDeviceBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceAnquanActivity extends BaseActivity {

    private Context context = AddDeviceAnquanActivity.this;

    @BindView(R.id.et_anquan)
    EditText etAnquan;

    private String type = "";
    private String xlh = "";
    private String xinghao = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_anquan);

        type = getIntent().getStringExtra("type");
        xlh = getIntent().getStringExtra("xlh");
        xinghao = getIntent().getStringExtra("xinghao");
        StatusBarUtils.setStatusBar(AddDeviceAnquanActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceAnquanActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.btn_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                next();
                break;
        }
    }

    private void next() {

        String anquan = etAnquan.getText().toString();
        if(StringUtils.isEmpty(anquan)){
            ToastUtil.showShort(context, "安全码不能为空");
        }else {

            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("deviceSn", xlh);
            map.put("userId", SpUtils.getUserId(context));
            map.put("deviceSecurityCode", anquan);
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

                }
            });

        }

    }

}
