package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetBindDeviceDetailBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexSetDeviceInfoActivity extends BaseActivity {

    private Context context = IndexSetDeviceInfoActivity.this;

    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;
    @BindView(R.id.tv_device_model)
    TextView tvDeviceModel;
    @BindView(R.id.tv_sncode)
    TextView tvSncode;

    private String id = "";
    private String sncode = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_set_device_info);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(IndexSetDeviceInfoActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(IndexSetDeviceInfoActivity.this);
        initData();

    }

    private void initData() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("bindDeviceId", id);
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getBindDeviceDetail, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GetBindDeviceDetailBean bean = gson.fromJson(s, GetBindDeviceDetailBean.class);
                tvDeviceName.setText(bean.getData().getDeviceName());
                tvDeviceModel.setText(bean.getData().getDeviceModal());
                sncode = bean.getData().getSnCode();
                tvSncode.setText(bean.getData().getSnCode());
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.tv_copy})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_copy:
                StringUtils.copy(sncode, context);
                ToastUtil.showShort(context, "已复制");
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
