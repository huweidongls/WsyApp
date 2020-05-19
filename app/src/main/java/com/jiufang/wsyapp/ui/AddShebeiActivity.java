package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddShebeiActivity extends BaseActivity {

    private Context context = AddShebeiActivity.this;

    @BindView(R.id.et_xlh)
    EditText etXlh;
    @BindView(R.id.et_anquan)
    EditText etAnquan;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.tv_brand)
    TextView tvBrand;
    @BindView(R.id.btn_next)
    Button btnNext;

    private EasyPopup easyPopup;

    private String type = "";//1乐橙 2萤石

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shebei);

        StatusBarUtils.setStatusBar(AddShebeiActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddShebeiActivity.this);
        initData();

    }

    private void initData() {

        etXlh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                if(!StringUtils.isEmpty(type)&&!StringUtils.isEmpty(s)){
                    btnNext.setBackgroundResource(R.drawable.bg_ffa16f_3dp);
                    btnNext.setEnabled(true);
                }
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_select, R.id.btn_next})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_select:
                showSelect();
                break;
            case R.id.btn_next:
                String xlh = etXlh.getText().toString();
                String anquan = etAnquan.getText().toString();
                if(StringUtils.isEmpty(type)&&StringUtils.isEmpty(xlh)){
                    ToastUtil.showShort(context, "请完善信息");
                }else {
//                    intent.setClass(context, SelectShebeiActivity.class);
//                    intent.putExtra("type", type);
//                    intent.putExtra("xlh", xlh);
//                    if(!StringUtils.isEmpty(anquan)){
//                        intent.putExtra("anquan", anquan);
//                    }
//                    startActivity(intent);
                    addDevice(type, xlh, anquan);
                }
                break;
        }
    }

    /**
     * 添加设备
     */
    private void addDevice(String type, String xlh, String anquan) {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("snCode", xlh);
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.checkDeviceBindStatus, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {

            }
        });

//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("userId", SpUtils.getUserId(context));
//        map.put("deviceSn", xlh);
//        if(!StringUtils.isEmpty(anquan)){
//            map.put("deviceSecurityCode", anquan);
//        }
//        ViseUtil.Post(context, NetUrl.bindDevice, map, new ViseUtil.ViseListener() {
//            @Override
//            public void onReturn(String s) {
//
//            }
//        });

    }

    private void showSelect() {

        easyPopup = EasyPopup.create(AddShebeiActivity.this)
                .setContentView(R.layout.popupwindow_select_shebei)
                .setWidth(LinearLayout.LayoutParams.MATCH_PARENT)
                .setFocusAndOutsideEnable(true)
                .apply();
        easyPopup.showAtAnchorView(rlSelect, YGravity.BELOW, XGravity.ALIGN_LEFT|XGravity.ALIGN_RIGHT, 0, 0);

        TextView tvLc = easyPopup.findViewById(R.id.tv_lc);
        TextView tvYs = easyPopup.findViewById(R.id.tv_ys);
        tvLc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvBrand.setText("乐橙");
                type = "1";
                if(!StringUtils.isEmpty(type)&&!StringUtils.isEmpty(etXlh.getText().toString())){
                    btnNext.setBackgroundResource(R.drawable.bg_ffa16f_3dp);
                    btnNext.setEnabled(true);
                }
                easyPopup.dismiss();
            }
        });
        tvYs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvBrand.setText("萤石");
                type = "2";
                if(!StringUtils.isEmpty(type)&&!StringUtils.isEmpty(etXlh.getText().toString())){
                    btnNext.setBackgroundResource(R.drawable.bg_ffa16f_3dp);
                    btnNext.setEnabled(true);
                }
                easyPopup.dismiss();
            }
        });

    }

}
