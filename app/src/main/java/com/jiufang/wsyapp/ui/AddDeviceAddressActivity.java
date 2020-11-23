package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetAreaByNameBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceAddressActivity extends BaseActivity {

    private Context context = AddDeviceAddressActivity.this;

    @BindView(R.id.et_device_name)
    EditText etDeviceName;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_map)
    TextView etMap;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_police)
    TextView tvPolice;

    private String id = "";
    private String area = "";
    private String type = "";//0为绑定设备跳过来        1为播放详情一键报警跳过来

    private String policeId = "";
    private String policeName = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_address);

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        StatusBarUtils.setStatusBar(AddDeviceAddressActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceAddressActivity.this);
        initData();

    }

    private void initData() {

        if(type.equals("0")){
            tv.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
        }else if(type.equals("1")){
            tv.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
        }
        setEditTextInhibitInputSpaChat(etDeviceName);
        setEditTextInhibitInputSpaChat(etName);

    }

    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public void setEditTextInhibitInputSpaChat(EditText editText) {
        InputFilter filter_space = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" "))
                    return "";
                else
                    return null;
            }
        };
        InputFilter filter_speChat = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                String speChat = "[`~!@#_$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）— +|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(charSequence.toString());
                if (matcher.find()) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter_space, filter_speChat});
    }

    @OnClick({R.id.rl_back, R.id.btn_complete, R.id.tv_map, R.id.ll_map, R.id.rl_police})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_complete:
                onComplete();
                break;
            case R.id.tv_map:
                intent.setClass(context, AddDeviceMapActivity.class);
                startActivityForResult(intent, 1001);
                break;
            case R.id.ll_map:
                intent.setClass(context, AddDeviceMapActivity.class);
                startActivityForResult(intent, 1001);
                break;
            case R.id.rl_police:
                intent.setClass(context, PoliceListActivity.class);
                startActivityForResult(intent, 1002);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null&&requestCode == 1001){
            PoiInfo poiInfo = data.getParcelableExtra("bean");
            etMap.setText(poiInfo.getAddress()+"-"+poiInfo.getName());
            area = poiInfo.getCity();
            Logger.e("123123", area);
        }

        if(data != null&&requestCode == 1002){
            policeId = data.getStringExtra("id");
            policeName = data.getStringExtra("name");
            tvPolice.setText(policeName);
            Logger.e("123123", policeId+"--"+policeName);
        }

    }

    private void onComplete() {

        String deviceName = etDeviceName.getText().toString();
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String map = etMap.getText().toString();
        String address = etAddress.getText().toString();

        if(StringUtils.isEmpty(name)||StringUtils.isEmpty(phone)
                ||StringUtils.isEmpty(map)||StringUtils.isEmpty(address)
                ||StringUtils.isEmpty(policeId)||StringUtils.isEmpty(policeName)){
            ToastUtil.showShort(context, "请完善信息");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待");
            Map<String, String> map1 = new LinkedHashMap<>();
            map1.put("areaName", area);
            ViseUtil.Post(context, NetUrl.getAreaByName, map1, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Logger.e("123123", s);
                    Gson gson = new Gson();
                    GetAreaByNameBean bean = gson.fromJson(s, GetAreaByNameBean.class);
                    int areaId = bean.getData().getId();
                    Map<String, String> map2 = new LinkedHashMap<>();
                    map2.put("address", map);
                    map2.put("areaId", areaId+"");
                    map2.put("bindDeviceId", id);
                    if(!StringUtils.isEmpty(deviceName)){
                        map2.put("bindDeviceName", deviceName);
                    }
                    map2.put("houseNumber", address);
                    map2.put("personName", name);
                    map2.put("personPhone", phone);
                    map2.put("policeStationId", policeId);
                    map2.put("policeStationName", policeName);
                    String asd = gson.toJson(map2);
                    Logger.e("123123", asd);
                    ViseUtil.Post(context, NetUrl.updateBindDeviceUsePerson, map2, dialog, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            if(type.equals("0")){
                                ToastUtil.showShort(context, "设置成功");
                                Intent intent = new Intent();
                                intent.setClass(context, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else if(type.equals("1")){
                                ToastUtil.showShort(context, "设置成功");
                                finish();
                            }
                        }

                        @Override
                        public void onElse(String s) {

                        }
                    });
                }

                @Override
                public void onElse(String s) {

                }
            });
        }

    }

}
