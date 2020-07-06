package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetComboOrderDetailBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoumaijiluDetailsActivity extends BaseActivity {

    private Context context = GoumaijiluDetailsActivity.this;

    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_fuzeren)
    TextView tvFuzeren;
    @BindView(R.id.tv_taocan_type)
    TextView tvTaocanType;
    @BindView(R.id.tv_taocan_daima)
    TextView tvTaocanDaima;
    @BindView(R.id.tv_taocan_time)
    TextView tvTaocanTime;
    @BindView(R.id.tv_brand)
    TextView tvBrand;
    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;
    @BindView(R.id.tv_device_model)
    TextView tvDeviceModel;
    @BindView(R.id.tv_device_sn)
    TextView tvDeviceSn;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goumaijilu_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(GoumaijiluDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(GoumaijiluDetailsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("comboOrderId", id);
        ViseUtil.Post(context, NetUrl.getComboOrderDetail, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetComboOrderDetailBean bean = gson.fromJson(s, GetComboOrderDetailBean.class);
                tvOrderId.setText(bean.getData().getOrderSn());
                tvTime.setText(bean.getData().getCreateTime());
                tvPrice.setText(StringUtils.roundByScale(bean.getData().getActualAmount(), 2));
                int payType = bean.getData().getPayMethod();
                if(payType == 1){
                    tvPayType.setText("线下交易");
                }else {
                    tvPayType.setText("线上交易");
                }
                tvFuzeren.setText(bean.getData().getSysUserName());
                tvTaocanType.setText(bean.getData().getComboCategoryName());
                tvTaocanDaima.setText(bean.getData().getComboCode());
                tvTaocanTime.setText(bean.getData().getComboStartTime()+"~"+bean.getData().getComboExpireTime());
                tvBrand.setText(bean.getData().getBrandName());
                tvDeviceName.setText(bean.getData().getDeviceName());
                tvDeviceModel.setText(bean.getData().getDeviceModel());
                tvDeviceSn.setText(bean.getData().getSnCode());
            }

            @Override
            public void onElse(String s) {

            }
        });

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
