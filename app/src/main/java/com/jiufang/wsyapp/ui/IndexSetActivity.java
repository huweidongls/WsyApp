package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetDeviceDetailInfoBean;
import com.jiufang.wsyapp.dialog.DialogCustom;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexSetActivity extends BaseActivity {

    private Context context = IndexSetActivity.this;

    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;
    @BindView(R.id.tv_sncode)
    TextView tvSncode;
    @BindView(R.id.tv_cloud)
    TextView tvCloud;
    @BindView(R.id.tv_na)
    TextView tvNa;
    @BindView(R.id.iv_jingbao)
    ImageView ivJingbao;
    @BindView(R.id.tv_areaname)
    TextView tvAreaName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_yinpin)
    LinearLayout llYinpin;
    @BindView(R.id.rl_set_more_lc)
    RelativeLayout rlSetMoreLc;
    @BindView(R.id.iv_yinpin)
    ImageView ivYinpin;
    @BindView(R.id.iv_title)
    ImageView ivTitle;

    private String id = "";
    private int brandId = 0;
    private int isNotice = 0;
    private int yinpin = 0;
    private String xlh = "";
    private String anquan = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_set);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(IndexSetActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(IndexSetActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("bindDeviceId", id);
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getDeviceDetailInfo, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetDeviceDetailInfoBean bean = gson.fromJson(s, GetDeviceDetailInfoBean.class);
                brandId = bean.getData().getBrandId();
                xlh = bean.getData().getSnCode();
                anquan = bean.getData().getSecurityCode();
                if(brandId == 1){
                    llYinpin.setVisibility(View.VISIBLE);
                    rlSetMoreLc.setVisibility(View.VISIBLE);
                    Map<String, String> map1 = new LinkedHashMap<>();
                    map1.put("deviceId", id);
                    map1.put("enableType", "audioEncodeControl");
                    map1.put("userId", SpUtils.getUserId(context));
                    ViseUtil.Post(context, NetUrl.getLcDeviceCapabilityStatus, map1, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                if(jsonObject.optString("data").equals("on")){
                                    yinpin = 1;
                                    ivYinpin.setImageResource(R.mipmap.turn_on);
                                }else if(jsonObject.optString("data").equals("off")){
                                    yinpin = 0;
                                    ivYinpin.setImageResource(R.mipmap.turn_off);
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
                tvDeviceName.setText(bean.getData().getDeviceName());
                tvSncode.setText(bean.getData().getSnCode());
                int cloud = bean.getData().getCloudStorageStatus();
                if(cloud == 0){
                    tvCloud.setText("未开启");
                }else if(cloud == 1){
                    tvCloud.setText("已开启");
                }else if(cloud == 2){
                    tvCloud.setText("即将到期");
                }else if(cloud == 3){
                    tvCloud.setText("已过期");
                }
                int na = bean.getData().getNativeStorageStatus();
                if(na == 0){
                    tvNa.setText("未检测到储存介质");
                }else if(na == 1){
                    tvNa.setText("已开启");
                }
                isNotice = bean.getData().getIsNotice();
                if(isNotice == 0){
                    ivJingbao.setImageResource(R.mipmap.turn_off);
                }else if(isNotice == 1){
                    ivJingbao.setImageResource(R.mipmap.turn_on);
                }
                tvAreaName.setText(bean.getData().getAreaFullName());
                tvAddress.setText(bean.getData().getAddress());
                tvName.setText(bean.getData().getPersonName()+"  "+toPhone(bean.getData().getPersonPhone()));
                String pic = bean.getData().getSnapImage();
                if(pic.length()>2){
                    if(pic.substring(0, 1).equals("/")){
                        pic = pic.substring(1, pic.length());
                    }
                    Glide.with(context).load(NetUrl.BASE_IMG_URL+pic).into(ivTitle);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.ll_device_info, R.id.rl_zhence, R.id.rl_set_more_lc, R.id.iv_jingbao, R.id.rl_edit, R.id.iv_yinpin,
    R.id.rl_wifi})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_wifi:
                DialogCustom dialogCustom = new DialogCustom(context, "为设备配置网络需要重置设备，听到设备重置提示音请点击确定去配置网络！", new DialogCustom.ClickListener() {
                    @Override
                    public void onSure() {
                        Intent intent1 = new Intent();
                        intent1.setClass(context, AddDeviceWifiActivity.class);
                        intent1.putExtra("tiaozhuan", "2");
                        intent1.putExtra("type", brandId+"");
                        intent1.putExtra("xlh", xlh);
                        intent1.putExtra("anquan", anquan);
                        startActivity(intent1);
                    }
                });
                dialogCustom.show();
                break;
            case R.id.iv_yinpin:
                if(yinpin == 0){
                    setYinpin(1);
                }else if(yinpin == 1){
                    setYinpin(0);
                }
                break;
            case R.id.rl_edit:
                intent.setClass(context, AddDeviceAddressActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.iv_jingbao:
                if(isNotice == 0){
                    setNotice(1);
                }else if(isNotice == 1){
                    setNotice(0);
                }
                break;
            case R.id.rl_set_more_lc:
                intent.setClass(context, IndexSetMoreLcActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case R.id.rl_zhence:
                if(brandId == 1){
                    intent.setClass(context, ZhencebufangLcActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else if(brandId == 2){
                    intent.setClass(context, ZhencebufangYsActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
                break;
            case R.id.ll_device_info:
                intent.setClass(context, IndexSetDeviceInfoActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("brandId", brandId+"");
                startActivity(intent);
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    /**
     * 设置设备音频
     * @param i
     */
    private void setYinpin(int i) {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        if(i == 1){
            map.put("enable", "true");
        }else if(i == 0){
            map.put("enable", "false");
        }
        map.put("enableType", "audioEncodeControl");
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setLcDeviceCapabilityStatus, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                if(i == 0){
                    yinpin = 0;
                    ivYinpin.setImageResource(R.mipmap.turn_off);
                }else if(i == 1){
                    yinpin = 1;
                    ivYinpin.setImageResource(R.mipmap.turn_on);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    private void setNotice(int isNo){
        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("bindDeviceId", id);
        map.put("isNotice", isNo+"");
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setDeviceNoticeStatus, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                if(isNo == 1){
                    isNotice = 1;
                    ivJingbao.setImageResource(R.mipmap.turn_on);
                }else if(isNo == 0){
                    isNotice = 0;
                    ivJingbao.setImageResource(R.mipmap.turn_off);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });
    }

}
