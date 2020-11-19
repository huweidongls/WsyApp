package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.CreateComboOrderBean;
import com.jiufang.wsyapp.bean.GetComboByIdBean;
import com.jiufang.wsyapp.bean.GetDeviceInfoBean;
import com.jiufang.wsyapp.dialog.DialogBuy;
import com.jiufang.wsyapp.dialog.DialogCustom;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaocanBuyActivity extends BaseActivity {

    private Context context = TaocanBuyActivity.this;

    @BindView(R.id.rl_orderid)
    RelativeLayout rlOrderId;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_taocan_name)
    TextView tvTaocanName;
    @BindView(R.id.tv_taocan_code)
    TextView tvTaocanCode;
    @BindView(R.id.tv_taocan_price)
    TextView tvTaocanPrice;
    @BindView(R.id.tv_device_type)
    TextView tvDeviceType;
    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;
    @BindView(R.id.tv_device_model)
    TextView tvDeviceModel;
    @BindView(R.id.tv_device_sn)
    TextView tvDeviceSn;

    private String deviceId = "";
    private String taocanId = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_buy);

        deviceId = getIntent().getStringExtra("deviceId");
        taocanId = getIntent().getStringExtra("taocanId");
        StatusBarUtils.setStatusBar(TaocanBuyActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(TaocanBuyActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("comboId", taocanId);
        ViseUtil.Post(context, NetUrl.getComboById, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GetComboByIdBean bean = gson.fromJson(s, GetComboByIdBean.class);
                tvTaocanName.setText(bean.getData().getComboName());
                tvTaocanCode.setText(bean.getData().getComboCode());
                tvTaocanPrice.setText("￥"+bean.getData().getRetailPrice());
            }

            @Override
            public void onElse(String s) {

            }
        });

        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put("deviceId", deviceId);
        map1.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getDeviceInfo, map1, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GetDeviceInfoBean bean = gson.fromJson(s, GetDeviceInfoBean.class);
                int brandId = bean.getData().getBrandId();
                if(brandId == 1){
                    tvDeviceType.setText("乐橙");
                }else if(brandId == 2){
                    tvDeviceType.setText("萤石");
                }
                tvDeviceName.setText(bean.getData().getDeviceName());
                tvDeviceModel.setText(bean.getData().getDeviceModel());
                tvDeviceSn.setText(bean.getData().getDeviceSn());
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_buy, R.id.rl_call})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_buy:
                DialogCustom dialogCustom = new DialogCustom(context, "是否立即购买？", new DialogCustom.ClickListener() {
                    @Override
                    public void onSure() {
                        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("userId", SpUtils.getUserId(context));
                        map.put("bindDeviceId", deviceId);
                        map.put("comboId", taocanId);
                        ViseUtil.Post(context, NetUrl.createComboOrder, map, dialog, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                Logger.e("123123", s);
                                Gson gson = new Gson();
                                CreateComboOrderBean bean = gson.fromJson(s, CreateComboOrderBean.class);
                                rlOrderId.setVisibility(View.VISIBLE);
                                tvOrderId.setText(bean.getData().getOrderSn());
                                ToastUtil.showShort(context, "创建订单成功，请联系销售人员购买");
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }
                });
                dialogCustom.show();
                break;
            case R.id.rl_call:
                DialogBuy dialogBuy = new DialogBuy(context);
                dialogBuy.show();
                break;
        }
    }

}
